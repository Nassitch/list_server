FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY mvnw .
COPY .mvn ./.mvn
COPY pom.xml ./
COPY src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre

RUN mkdir -p /app/upload

COPY --from=build /app/target/server-0.0.1-SNAPSHOT.jar /app/server-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/server-0.0.1-SNAPSHOT.jar"]