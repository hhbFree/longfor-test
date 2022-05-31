#!/bin/bash

# 此命令只能初始化拉取一次
# git拉取配置
cd ~
urcs_dir=`pwd`

if [ -d "urcs-docker" ]; then
  cd $urcs_dir/urcs-docker
  git pull
  cd sh
else
  git clone http://127.0.0.1:3343/5pg/urcs-docker.git
  cd $urcs_dir/urcs-docker/sh
fi

# 判断 docker 是否已经安装
if command -v docker >/dev/null 2>&1; then
  sudo bash docker-centos-install.sh
else
  sudo bash docker-centos-install.sh install
fi

# 判断 docker-compose 是否已经安装
if command -v docker-compose >/dev/null 2>&1; then
  echo `docker-compose -v`
else
  sudo bash docker-compose-install.sh
fi

# 登录docker用户组
sudo newgrp docker