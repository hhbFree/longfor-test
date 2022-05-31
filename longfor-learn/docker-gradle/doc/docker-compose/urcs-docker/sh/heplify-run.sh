#!/bin/bash

# Check if user is root
if [ $(id -u) != "0" ]; then
    echo " Not the root user! Try using sudo Command ! "
    exit 1
fi

# $1 目标服务器地址
exec nohup ./heplify -hs "$1:9060" &