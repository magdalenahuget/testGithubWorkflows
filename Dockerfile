# Dockerfile
FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp
RUN mvn clean package
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]