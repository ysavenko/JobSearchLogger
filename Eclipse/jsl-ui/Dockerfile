FROM openjdk:17

ARG ARG_EXPOSE_PORT=8080
ARG ARG_JAR_FILE=app.jar
ARG ARG_HOST_USERS=users
ARG ARG_HOST_CONTACTS=contacts
ARG ARG_HOST_POSITIONS=positions

ENV HOST_USERS=${ARG_HOST_USERS}
ENV HOST_CONTACTS=${ARG_HOST_CONTACTS}
ENV HOST_POSITIONS=${ARG_HOST_POSITIONS}

EXPOSE ${ARG_EXPOSE_PORT}
ADD ${ARG_JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--users.host=${HOST_USERS}", "--jobContacts.host=${HOST_CONTACTS}", "--positions.host=${HOST_POSITIONS}"]