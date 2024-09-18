FROM openjdk:11-jre-slim
VOLUME /tmp
ARG JAR_FILE=target/football-championship-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [“java”,“-jar”,“/app.jar”]