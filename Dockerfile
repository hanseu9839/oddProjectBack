FROM openjdk:11-jdk
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew


FROM openjdk:11-jdk
COPY --from=builder build/libs/*.jar app.jar

ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=$ {ENVIRONMENT}

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

