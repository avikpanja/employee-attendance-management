#FROM eclipse-temurin:11.0.22_7-jdk-alpine
FROM adoptopenjdk/openjdk11
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]