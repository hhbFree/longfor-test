 docker pull registry:2
 docker run -itd --name registry --restart=always  -p 5000:5000 -v /registry:/var/lib/registry registry:2