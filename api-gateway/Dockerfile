FROM eclipse-temurin:17.0.10_7-jdk-alpine
#FROM adoptopenjdk/openjdk17
VOLUME /tmp
COPY target/*.jar app.jar
ENV DOCKER_TLS_VERIFY=0
ENTRYPOINT ["java","-jar","/app.jar"]