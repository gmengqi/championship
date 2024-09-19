# FROM openjdk:11-jre-slim
# VOLUME /tmp
# ARG JAR_FILE=target/football-championship-0.0.1-SNAPSHOT.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT [“java”,“-jar”,“/app.jar”]
FROM openjdk:17-slim
VOLUME /tmp
EXPOSE 8080
COPY target/football-championship-0.0.1-SNAPSHOT.jar football-championship.jar
ENTRYPOINT ["java","-jar","/football-championship.jar"]