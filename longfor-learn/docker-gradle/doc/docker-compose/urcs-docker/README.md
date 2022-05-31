# URCS 部署

GIT拉取当前配置
```
git clone http://127.0.0.1:3343/5pg/urcs-docker.git
```
或http拉取配置
```
wget http://127.0.0.1:3343/5pg/urcs-docker/repository/archive.zip 
curl -L http://127.0.0.1:3343/5pg/urcs-docker/repository/archive.zip -o archive.zip 
```
如果新环境没有配置UPF和终端IP的路由需要手工配置,否则UPF请求RCS链路会有问题。请参考第八节【网络配置】


## 一、安装 docker
### Ubuntu系统 >= 16
执行 [docker-ubuntu-install.sh](sh/docker-ubuntu-install.sh)  脚本
```
# 安装 docker 并且配置 daemon.json 文件
sudo bash docker-ubuntu-install.sh install

# 只配置 daemon.json 文件
sudo bash docker-ubuntu-install.sh 
```
### Centos系统 >= 7
执行 [docker-centos-install.sh](sh/docker-centos-install.sh)  脚本
```
# 安装 docker 并且配置 daemon.json 文件
sudo bash docker-centos-install.sh install

# 只配置 daemon.json 文件
sudo bash docker-centos-install.sh
```

## 二、安装 docker-compose
执行 [docker-compose-install.sh](sh/docker-compose-install.sh) 脚本
```
sudo bash docker-compose-install.sh
```

## 三、服务组件简介

|文件|说明|包含组件|
|---|---|---|
|[.env](docker/.env) | 所有服务依赖的环境变量 ||
|[compose-base.yaml](docker/compose-base.yaml)| 基础组件| zk,redis,mysql,fastDFS|
|[compose-ims.yaml](docker/compose-ims.yaml)| ims组件| sbc,cscf,im|
|[compose-rcs.yaml](docker/compose-rcs.yaml)| rcs组件| rcs-mysql,group,dm,nfs,maap|

### 服务依赖关系
```
compose-rcs > compose-ims > compose-base
```

### 环境变量主要参数

* PRIVATE_IP  
  主机内网IP，建议配置在主机环境变量中，也可以直接修改.env文件

* PUBLIC_IP  
  主机公网IP，默认使用PRIVATE_IP，建议配置在主机环境变量中，也可以直接修改.env文件。如果没有建议使用内网IP。

* XGC_HOST  
  核心网IP，默认使用PRIVATE_IP。建议启动前修改文件
  
### 如果出现端口冲突情况

如果出现端口冲突，在可以修改.env配置，如果是组件默认端口冲突，参数不在.env文件内，可以查阅相关组件配置，并添加相关参数指定新端口即可。

[端口使用详情](md/rcs_build.md)

## 四、部署IMS

### 1、先部署基础服务
#### 基础服务包括

|服务名称|说明|
|---|---|
|ims-mysql| ims服务依赖的mysql数据库，包括ims所需要的db和tables|
|ims-redis| redis服务|
|ims-zookeeper| zookeeper服务|
|ims-fastdfs-tracker| fastdfs tracker服务|
|ims-fastdfs-storage| fastdfs storage服务|

#### 服务管理
创建并运行服务
```shell
# 全部服务
docker-compose -f compose-base.yaml up -d
# 单个服务
docker-compose -f compose-base.yaml up -d ims-mysql

# -d 后台运行
```

重启服务
```shell
# 全部服务
docker-compose -f compose-base.yaml restart
# 单个服务
docker-compose -f compose-base.yaml restart ims-mysql
```

停止服务
```shell
# 全部服务
docker-compose -f compose-base.yaml stop
# 单个服务
docker-compose -f compose-base.yaml stop ims-mysql
```

启动服务
```shell
# 全部服务
docker-compose -f compose-base.yaml start
# 单个服务
docker-compose -f compose-base.yaml start ims-mysql
```

卸载服务
```shell
# 全部服务
docker-compose -f compose-base.yaml down
# 单个服务
docker-compose -f compose-base.yaml down ims-mysql
```

### 2、在部署IMS服务
#### IMS服务包括

|服务名称|说明|功能|
|---|---|---|
|urcs-as-sbc| 会话边界控制器 |作为IMS网络的SIP和RTSP的 Proxy Server，所有的SIP与RTSP讯息都会透过SBC来处理，SBC更具备NAT穿透的功能|
|urcs-as-cscf| 呼叫会话控制功能 |CSCF网元主要负责和用户业务息息相关的注册和鉴权、会话控制、路由管理、网管和计费等功能的控制|
|urcs-as-im| 即时通信服务| 实现 短信，文本消息，富文本消费传递，维护消息队列|

#### 服务管理
创建并运行服务
```shell
# 全部服务
docker-compose -f compose-ims.yaml up -d
# 单个服务
docker-compose -f compose-ims.yaml up -d urcs-as-im

# -d 后台运行
```

重启服务
```shell
# 全部服务
docker-compose -f compose-ims.yaml restart
# 单个服务
docker-compose -f compose-ims.yaml restart urcs-as-im
```

停止服务
```shell
# 全部服务
docker-compose -f compose-ims.yaml stop
# 单个服务
docker-compose -f compose-ims.yaml stop urcs-as-im
```

启动服务
```shell
# 全部服务
docker-compose -f compose-ims.yaml start
# 单个服务
docker-compose -f compose-ims.yaml start urcs-as-im
```

卸载服务
```shell
# 全部服务
docker-compose -f compose-ims.yaml down
# 单个服务
docker-compose -f compose-ims.yaml down urcs-as-im
```

## 五、在部署RCS服务
#### RCS服务包括

|服务名称|说明|功能|
|---|---|---|
|rcs-mysql| mysql数据库 | rcs相关服务的db和tables|
|urcs-as-group| 群组服务 | 实现相关群消息和群管理功能|
|urcs-as-maap| MAAP服务 | 实现Chatbot消息推送和Chatbot管理功能|
|urcs-as-dm| device management |用于管理rcs设备配置 |
|urcs-as-nfs| 文件存储服务 |管理文件信息|
|urcs-as-tas| 会话控制功能 |同震，响铃，会控|
|urcs-as-im| 即时通信服务| 实现 短信，文本消息，富文本消费传递|

#### 服务管理
创建并运行服务
```shell
# 全部服务
docker-compose -f compose-rcs.yaml up -d
# 单个服务
docker-compose -f compose-rcs.yaml up -d urcs-as-dm

# -d 后台运行
```

重启服务
```shell
# 全部服务
docker-compose -f compose-rcs.yaml restart
# 单个服务
docker-compose -f compose-rcs.yaml restart urcs-as-dm
```

停止服务
```shell
# 全部服务
docker-compose -f compose-ims.yaml stop
# 单个服务
docker-compose -f compose-ims.yaml stop urcs-as-im
```

启动服务
```shell
# 全部服务
docker-compose -f compose-ims.yaml start
# 单个服务
docker-compose -f compose-ims.yaml start urcs-as-dm
```

卸载服务
```shell
# 全部服务
docker-compose -f compose-rcs.yaml down
# 单个服务
docker-compose -f compose-rcs.yaml down urcs-as-dm
```
## 六、一键部署RCS
此命令仅适用于初始化项目

配置系统IP
```
#PRIVATE_IP 内网 IP
#PUBLIC_IP  公网 IP
#XGC_HOST   5GC IP

export PRIVATE_IP=172.16.160.197
export PUBLIC_IP=172.16.160.197
export XGC_HOST=172.16.160.197
```

### 1、ubuntu系统已经部署RCS
查看[install.sh](sh/install.sh)

下载[install.sh](http://127.0.0.1:3343/5pg/urcs-docker/raw/master/sh/install.sh)
```ssh
bash -c "$(curl -fsSL http://127.0.0.1:3343/5pg/urcs-docker/raw/master/sh/install.sh)"

或

curl -fsSL http://127.0.0.1:3343/5pg/urcs-docker/raw/master/sh/install.sh -o install.sh && bash install.sh 
```

### 2、centos系统已经部署RCS
查看[install-centos.sh](sh/install-centos.sh)

下载[install-centos.sh](http://127.0.0.1:3343/5pg/urcs-docker/raw/master/sh/install-centos.sh)
```ssh
bash -c "$(curl -fsSL http://127.0.0.1:3343/5pg/urcs-docker/raw/master/sh/install-centos.sh)"

或

curl -fsSL http://127.0.0.1:3343/5pg/urcs-docker/raw/master/sh/install-centos.sh -o install-centos.sh && bash install-centos.sh
```
## 七、更新服务
查看[xgc.sh](sh/xgc.sh)

下载[xgc.sh](http://127.0.0.1:3343/5pg/urcs-docker/raw/master/sh/xgc.sh)
```ssh
curl -fsSL http://127.0.0.1:3343/5pg/urcs-docker/raw/master/sh/xgc.sh -o urcs.sh && bash xgc.sh -h
```

## 八、一键卸载所有容器
查看[uninstall.sh](sh/uninstall.sh)

下载[uninstall.sh](http://127.0.0.1:3343/5pg/urcs-docker/raw/master/sh/install.sh)
```ssh
bash -c "$(curl -fsSL http://127.0.0.1:3343/5pg/urcs-docker/raw/master/sh/uninstall.sh)"

或

curl -fsSL http://127.0.0.1:3343/5pg/urcs-docker/raw/master/sh/uninstall.sh -o uninstall.sh && bash uninstall.sh 
```

## 九、网络配置

### static

```
sudo vim /etc/netplan/00-installer-config.yaml
```

```
network:
 ethernets:
   enp1s0:
     dhcp4: no
     dhcp6: no
     addresses:
       - 172.16.160.197/24
     gateway4: 172.16.160.1
     nameservers:
       addresses: [8.8.8.8, 114.114.114.114]
     routes:
       - to: 222.222.2.0/24
         via: 172.16.160.102
     routing-policy:
       - from: 0.0.0.0/24
 version: 2
```

```
sudo netplan apply
```
### dynamic

- 命令配置:机器重启后配置生效

```
# UPF IP是：172.16.160.102
# 终端 IP段是：222.222.1.0
# UPF和RCS所在网络的网关IP为：172.16.160.100

# 配置案例 UPF路由
sudo route add -net 192.168.200.0/24 gw 172.16.160.100
sudo route add -net 192.168.210.0/24 gw 172.16.160.100
sudo route add -net 192.168.220.0/24 gw 172.16.160.100

# 终端IP路由回UPF IP
sudo route add -net 222.222.1.0/24 gw 172.16.160.102

# 删除路由
sudo route del -net 222.222.1.0/24 gw 172.16.160.102
```
