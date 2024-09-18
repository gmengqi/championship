FROM openjdk:11-jre-slim
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY football-championship-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [“java”,“-jar”,“/app.jar”]