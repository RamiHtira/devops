FROM openjdk:8-jre-alpine
ADD target/tpAchatProject-1.0.jar devopsProjet.jar
ENTRYPOINT ["java","-jar","/devopsProjet.jar"]