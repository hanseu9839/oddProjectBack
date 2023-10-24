#FROM openjdk:11-jdk
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .
#COPY src src
#RUN chmod +x ./gradlew


FROM openjdk:11-jdk
LABEL maintainer="hanseu9839@gmail.com"
ARG JAR_FILE=build/libs/oddProject-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} odd.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","odd.jar"]

