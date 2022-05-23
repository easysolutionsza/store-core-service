FROM openjdk:8-jdk-alpine
RUN addgroup -S storedocker && adduser -S storedocker -G storedocker
USER storedocker:storedocker
ARG JAR_FILE=/home/himanshu/Documents/myprojects/store-core-service/target/*.jar
COPY target/store-core-service-0.0.1-SNAPSHOT.jar store-core-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/store-core-service-0.0.1-SNAPSHOT.jar"]