FROM openjdk:17
ARG ARG_EXPOSE_PORT=8080
ARG ARG_JAR_FILE=app.jar
ARG ARG_JAVA_PARAMS=DEF
ENV JAVA_PARAMS=${ARG_JAVA_PARAMS}
EXPOSE ${ARG_EXPOSE_PORT}
ADD ${ARG_JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar", "--users.host=http://jsl-contact:8081"]
ENTRYPOINT ["java","-jar","/app.jar", "--users.host=${JAVA_PARAMS}"]
#ENTRYPOINT ["java","-jar","/app.jar", "${JAVA_PARAMS}"]