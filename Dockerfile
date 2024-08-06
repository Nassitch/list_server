FROM azul/zulu-openjdk-alpine:11
VOLUME /tmp
EXPOSE 8080
COPY target/server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
