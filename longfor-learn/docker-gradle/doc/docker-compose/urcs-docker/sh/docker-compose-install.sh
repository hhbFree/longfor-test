#!/bin/bash

## 安装 docker-compose
sudo curl -L "https://github.com/docker/compose/releases/download/1.26.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
##  创建软链
sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
docker-compose --version