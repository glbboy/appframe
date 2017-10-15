FROM java:8
VOLUME /tmp
ADD target/appframe-config-server-0.0.1.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 7101
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
