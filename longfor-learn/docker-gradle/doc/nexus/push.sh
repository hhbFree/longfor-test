mkdir /some/dir/nexus-data && chown -R 200 /some/dir/nexus-data
docker run -d -p 8081:8081 --name nexusdocker-ubuntu-install.sh -v /some/dir/nexus-data:/nexus-data sonatype/nexus3