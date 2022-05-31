#!/bin/bash

tag=172.16.157.64:5000/jenkins:1.0

VERSION=4.7
wget https://services.gradle.org/distributions/gradle-${VERSION}-bin.zip ./

docker build  -t ${tag} .

docker push ${tag}

rm -rf /gradle*

docker run -p 8083:8080 -p 50000:50000 -d  \
-u root \
-v /root/dockerfile/jenkins/data:/var/jenkins_home \
-v /var/run/docker.sock:/var/run/docker.sock \
-v /usr/bin/docker:/usr/bin/docker \
--name jenkins \
10.10.208.193:5000/jenkins:1.0