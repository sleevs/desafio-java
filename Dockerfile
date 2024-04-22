FROM openjdk:17-jdk-alpine AS desafio
ARG JAR_FILE=target/*.jar
COPY ./target/desafio-java-0.0.1-SNAPSHOT.jar /app/desafio-java.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]