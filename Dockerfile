FROM adoptopenjdk/openjdk17:alpine

WORKDIR /app

RUN apk --no-cache add \
    nodejs npm \
    curl \
    bash \
    && npm install -g wscat

RUN addgroup -S chat_admin && adduser -S -G chat_admin chat_admin

RUN chown -R chat_admin:chat_admin /app

USER chat_admin

COPY target/javachat.jar .

EXPOSE 8080

CMD ["java", "-jar", "javachat.jar"]