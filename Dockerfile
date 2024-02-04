FROM eclipse-temurin:18-jre-alpine
WORKDIR /app
COPY build/libs/monumentBook-0.0.1-SNAPSHOT.jar mb_api.jar
ENTRYPOINT ["java", "-jar", "mb_api.jar"]
