FROM openjdk:8-jre-alpine
ADD target/tpAchatProject-1.0.jar achat.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/achat.jar"]