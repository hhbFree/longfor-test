#!/bin/bash

tag=10.10.208.193:5000/ims-mysql:1.0

docker rmi ${tag}

docker build  -t ${tag} .

if [ "push" == "$1" ]; then
  docker push ${tag}
fi