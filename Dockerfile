FROM eclipse-temurin:18-jre-alpine
WORKDIR /app
COPY build/libs/reportsystem-0.0.1-SNAPSHOT.jar rs_api.jar
ENTRYPOINT ["java", "-jar", "rs_api.jar"]
