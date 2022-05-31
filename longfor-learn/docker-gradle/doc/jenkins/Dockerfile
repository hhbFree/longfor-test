FROM jenkins/jenkins
LABEL OG=urcs.ultrapower.com.cn

ENV PATH=/tmp/gradle-4.7/bin:$PATH
VOLUME /var/run/docker.sock:/var/run/docker.sock
VOLUME /usr/bin/docker:/usr/bin/docker
WORKDIR /tmp
ADD gradle-4.7-bin.zip .
RUN unzip gradle-4.7-bin.zip