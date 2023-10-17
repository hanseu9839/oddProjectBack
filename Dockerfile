FROM openjdk:11 AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew oddJar


FROM openjdk:11-jdk
COPY --from= builder build/libs/*.jar app.jar

EXPOSE 3030
ENTRYPOINT ["java","-jar","/app.jar"]

