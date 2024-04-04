FROM eclipse-temurin:17.0.10_7-jdk-alpine
#FROM adoptopenjdk/openjdk17
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]