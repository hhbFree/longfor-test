#!/bin/bash

# 此命令只能初始化拉取一次
# git拉取配置
#脚本目录
sh_dir=$(dirname "$0")
tag_log=tag_log.txt
docker_log=docker_log.txt
#git地址
git_url=http://127.0.0.1:3343/5pg/urcs-docker.git
#本地克隆目录
git_clone_dir=urcs-docker

cd ~
urcs_dir=`pwd`

# 参数 -------------------------------------
# 打印的帮助信息
help_str="
$0 [option].. [ARG]...

参数说明：option=alfe:h
  -a   部署整个配置文件
  -l   git拉取配置
  -f   强制git拉取配置，覆盖本地文件
  -e   添加环境变量
  -h   帮助信息
  $0 -l           git拉取配置
  $0 -lf          强制git拉取配置，覆盖本地文件
  $0 -a all       更新所有服务
  $0 -a base      更新所有compose-base.yaml里面相关的服务， -a 配置文件名的系统名称
  $0 urcs-as-sbc urcs-as-cscf  更新SBC和CSCF服务
  $0 urcs-as-sbc:4.0.0.2102021815  更新SBC服务,指定临时版本号,临时版本号存在tag_log.txt
  $0 -e mnc=001 urcs-as-sbc  更新SBC服务,指定临时变量

  docker-compose
  yaml文件命名规则：compose-系统名称.yaml
    例如：compose-ims.yaml  compose-rcs.yaml
  docker服务版本号名称： tag_服务名称 如果有横杠(-)需要用下划线(_)代替
    例如：urcs-as-sbc服务的版本号是 tag_urcs_as_sbc

  注意：
     服务名及容器名称不允许重复
  调试脚本：
    bash -x $0
"
#--------------------------------------------
compose_cli="docker-compose "
docker_compose_cli_s=""
# 整个文件部署
a_cli=false
#---------------------------------------------
# 默认不拉Git
git_pull=false
# 默认强制拉取Git
force_pull=false

getopt_cmd=$(getopt -q alfe:h "$@" )
eval set -- "$getopt_cmd"

#for arg in $@ ; do
#  echo $arg
#done

while true
do
  case "$1" in
    -a)
      a_cli=true
       shift ;;
    -e)
      env_p=$2
      shift 2;;
    -l)
      git_pull=true
      shift ;;
    -f)
      force_pull=true
      shift ;;
    -h)
      echo -e "$help_str"
      exit ;;
    --)
       shift
       break ;;
    *)
       echo "命令无效：$1"
       exit 1 ;;
  esac
done

#-------------------------------------------------
# 更新配置
if [ -d "${git_clone_dir}" ] ; then
  if $git_pull ; then
    cd ${urcs_dir}/${git_clone_dir}
    if $force_pull ; then
      git fetch --all && git reset --hard origin/master && git pull
    else
      git pull
    fi
  fi
else
  git clone $git_url
fi

#---------------------------------------------
# 扫描文件
function files() {
  filenames=$(ls | grep -E "$1")
  for filename in $filenames
  do
    compose_cli="$compose_cli -f $filename "
  done
}

# 实际执行 docker-compose 命令
function execdocker() {
  echo "$(date "+%Y-%m-%d %H:%M:%S") $docker_compose_cli_s" >> $docker_log
  echo "$docker_compose_cli_s"
  $docker_compose_cli_s || true
}
#---------------------------------------------
# 有参数则部署
if [ $# -gt 0 ]; then
  # 执行目录
  cd $urcs_dir/$git_clone_dir/docker

  # 扫描yaml文件
  file_extension="\.(yaml|yml)$"
  if $a_cli ; then
    if [ "$1" == "all" ]; then
      files $file_extension
    else
      for arg in "$@"
      do
        files "$arg$file_extension"
      done
    fi
  else
    files $file_extension
  fi

  # 构建部署命令
  docker_compose_cli=$compose_cli

  # 添加 hostname环境变量
  docker_compose_cli="env hostname=$(hostname) $docker_compose_cli"

  # 添加环境变量
  if [ -n "$env_p" ]; then
    docker_compose_cli="env $env_p $docker_compose_cli"
  fi

  if $a_cli ; then
    # 部署所有
    docker_compose_cli_s="$docker_compose_cli up -d"
    # 执行 docker-compose 命令
    execdocker
  else
    # 一个或多个部署
    # 遍历所以参数
    for arg in "$@"
    do
      name_tag=(${arg//:/ })
      len=${#name_tag[@]}

      # 版本号处理
      if [ $len == 2 ]
      then
        # 解析带版本号并记录更新版本
        c_name=${name_tag[0]}
        c_tag=${name_tag[1]}
        tag_name=tag_${c_name//'-'/'_'}
        echo "$(date "+%Y-%m-%d %H:%M:%S") $arg" >> $tag_log
        docker_compose_cli_s="env $tag_name=$c_tag $docker_compose_cli up -d $c_name"
      else

        # 无版本号
        docker_compose_cli_s="$docker_compose_cli up -d $arg"
      fi

      # 执行 docker-compose 命令
      execdocker
    done
  fi
  docker ps -a
else
  echo "需要添加参数：服务名，系统名或all。帮助信息: $0 -h"
fi

#------------------------------------------------
