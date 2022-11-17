FROM openjdk:8-jre-alpine
ADD target/tpAchatProject-1.0.jar devopsProjet.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/devopsProjet.jar"]