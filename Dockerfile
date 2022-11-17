FROM openjdk:8-jre-alpine
ADD target/tpAchatProject-1.0.jar spring-app:latest.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/spring-app:latest.jar"]