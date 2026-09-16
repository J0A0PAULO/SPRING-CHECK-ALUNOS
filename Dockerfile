FROM eclipse-temurin:latest
LABEL maintainer="moreirajoaopaulo157@senar"
WORKDIR /app
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]