FROM mysql:5.7
LABEL OG=urcs.ultrapower.com.cn

COPY utf8mb4.cnf /etc/mysql/conf.d/utf8mb4.cnf
COPY ./sql /tmp/sql

RUN chmod 0444 /etc/mysql/conf.d/utf8mb4.cnf
RUN mv /tmp/sql/*.sql /docker-entrypoint-initdb.d/ \
   && chmod 777 /docker-entrypoint-initdb.d/*.sql

RUN rm -rf /tmp/sql