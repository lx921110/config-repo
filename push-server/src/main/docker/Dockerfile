FROM 192.168.1.10:5000/jre8-timezone
VOLUME /tmp
ADD push-server-1.0.jar /app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT java ${JAVA_OPS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
