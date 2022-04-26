FROM adoptopenjdk:11-jre-hotspot
COPY . /jwt-app.jar
ARG secrets
ENV SECRETS={secrets:-jwt_secret}
EXPOSE 8080
ENTRYPOINT ["java","-jar","/jwt-app.jar", "echo env var: ${SECRETS}"]
