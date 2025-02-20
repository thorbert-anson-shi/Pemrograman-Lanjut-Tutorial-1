FROM eclipse-temurin:23-jdk-alpine as builder

WORKDIR /src/eshop
COPY . .
RUN chmod +x && ./gradlew clean bootJar

FROM eclipse-temurin:23-jre-alpine as runner

ARG USER_NAME=eshop
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

RUN addgroup -g ${USER_GID} ${USER_NAME} \
    && adduser -h /opt/eshop -D -u ${USER_UID} -G ${USER_NAME} ${USER_NAME}

USER ${USER_NAME}
WORKDIR /opt/eshop
COPY --from=builder --chown=${USER_UID}:${USER_GID} /src/eshop/build/libs/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]