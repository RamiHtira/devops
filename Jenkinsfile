pipeline {
    agent any
    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    stages {
        stage('GIT Checkout') {
            steps {
                echo 'Pulling code from Git'
                git branch: 'borhen', 
                url: 'https://github.com/RamiHtira/devops.git'
            }
        }
        stage('Building JAR') {
            steps {
                echo 'Building with Maven'
                sh 'mvn clean install -DskipTests'
            }
        }
        stage("MVN Clean Install after getting   "){
                    steps {
                        sh """mvn clean install -Dskiptest """

                    }
                }

              stage("Sonar") {
                steps {

               sh "mvn clean verify  sonar:sonar \
                -Dsonar.projectKey=tpAchat \
                -Dsonar.host.url=http://192.168.1.139:9000 \
                -Dsonar.login=admin \
                -Dsonar.password=paradax"


                       }
             }


              stage("nexus") {
                     steps{
                        echo "deploy project on nexus"
                        nexusArtifactUploader artifacts: [
             		[
             			artifactId: 'tpAchatProject',
             			classifier: '',
             			file: 'target/tpAchatProject-1.0.jar',
             			type: 'jar'
             		]
             	   ],
             	   credentialsId: 'nexus3',
             	   groupId: 'com.esprit.examen',
             	   nexusUrl: '192.168.34.48:8081/repository/maven-releases',
                        nexusVersion: 'nexus3',
             	   protocol: 'http',
               	   repository: 'DeployementRepo',
             	   version: '1.0'
                     }
                  }

    }
}