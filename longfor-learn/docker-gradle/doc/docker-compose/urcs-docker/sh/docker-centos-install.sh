#!/bin/bash

# centos 7

# 安装并配置docker
# sudo bash docker-centos-install.sh install
# 配置docker
# sudo bash docker-centos-install.sh

# 安装
if [ "install" == "$1" ]; then
  sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine || true

  sudo yum install -y yum-utils

  sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo

  sudo yum install docker-ce docker-ce-cli containerd.io
fi

sudo mkdir -p /etc/docker || true
# 修改配置
sudo cat > /etc/docker/daemon.json <<-EOF
 {
 	"registry-mirrors":["https://docker.mirrors.ustc.edu.cn"],
 	"insecure-registries":[
 			"127.0.0.1:5000","127.0.0.1:5000"
 	],
 	"log-driver":"json-file",
 	"log-opts": {"max-size":"100m", "max-file":"2"},
   "storage-driver": "overlay2"
 }
EOF

# 添加用户组
sudo gpasswd -a ${USER} docker

# 重启服务配置生效
sudo systemctl daemon-reload
sudo systemctl restart docker

# 登录docker用户组
#sudo newgrp docker