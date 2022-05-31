#!/bin/bash

cd ~
urcs_dir=`pwd`
cd $urcs_dir/urcs-docker/docker

case $1 in
base)
  docker-compose -f compose-base.yaml down
  ;;
ims)
  docker-compose -f compose-ims.yaml down
  ;;
rcs)
  docker-compose -f compose-rcs.yaml down
  ;;
imsall)
  docker-compose -f compose-base.yaml -f compose-ims.yaml down
  ;;
rcsall)
  docker-compose -f compose-base.yaml -f compose-ims.yaml -f compose-rcs.yaml down
  ;;
all)
  docker rm -f $(docker ps -a|awk 'NR>1 {print $1}')
  docker volume prune -f
  docker ps -a
  ;;
*)
  echo "参数输入：base,ims,rcs,imsall,rcsall,all"
  exit 1
  ;;
esac
