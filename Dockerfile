#FROM openjdk:11-jdk
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .
#COPY src src
#RUN chmod +x ./gradlew


FROM openjdk:11-jdk
LABEL maintainer="hanseu9839@gmail.com"
# JAR_FILE 변수 정의 -> 기본적으로 jar file이 2개이기 때문에 이름을 특정해야함.
ARG JAR_FILE=build/libs/oddProject-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} docker-odd.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","docker-odd.jar"]

