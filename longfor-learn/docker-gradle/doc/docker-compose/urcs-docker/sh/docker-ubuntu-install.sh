#!/bin/bash

# To install Docker Engine, you need the 64-bit version of one of these Ubuntu versions:
#
# Ubuntu Focal 20.04 (LTS)
# Ubuntu Bionic 18.04 (LTS)
# Ubuntu Xenial 16.04 (LTS)
# 安装并配置docker
# sudo bash docker-ubuntu-install.sh install
# 配置docker
# sudo bash docker-ubuntu-install.sh

# 安装
if [ "install" == "$1" ]; then
  sudo apt-get remove docker docker-engine docker.io containerd runc || true
  sudo apt-get update
  sudo apt-get install \
      apt-transport-https \
      ca-certificates \
      curl \
      gnupg-agent \
      software-properties-common
  sudo add-apt-repository \
     "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
     $(lsb_release -cs) \
     stable"
  sudo apt-get update
  sudo apt-get install docker-ce docker-ce-cli containerd.io
fi

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
sudo service docker restart

# 登录docker用户组
#sudo newgrp docker