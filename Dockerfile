FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} jwt-app.jar
EXPOSE 3333
ENTRYPOINT ["java","-jar","/jwt-app.jar"]
