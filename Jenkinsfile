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
                -Dsonar.host.url=http://192.168.34.48:9000 \
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
               	   repository: 'deploymentRepo',
             	   version: '1.0'
                     }
                  }

    }
}

pipeline{
    agent any
    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }
    stages {
        stage('Getting project from Git') {
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

        stage('Cleaning the project') {
            steps{
                sh "mvn -B -DskipTests clean  "
            }
        }


        stage('Unit Tests') {
            steps{
                sh "mvn test "
            }
        }



        stage('Artifact Construction') {
            steps{
                sh "mvn -B -DskipTests package "
            }
        }




        stage("Sonar") {
            steps {

           sh "mvn clean verify  sonar:sonar \
            -Dsonar.projectKey=tpAchat \
            -Dsonar.host.url=http://192.168.34.48:9000 \
            -Dsonar.login=admin \
            -Dsonar.password=paradax"


                   }
         }



        stage('Publish to Nexus') {
            steps {
                script {

configFileProvider([configFile(fileId: 'maven-settings', variable: 'settings')]) {
  sh 'mvn  -B -DskipTests deploy -s $settings'}

                }
            }
        }
          stage('Build Docker Image') {
                      steps {
                          script {
                            sh 'docker build -t spring-app:latest .'
                          }
                      }
                  }
                  stage('Push Docker Image') {
                      steps {
                          script {
                           withCredentials([string(credentialsId: 'jenkins', variable: 'dckr_pat_Fy1n8Guloriu8eUXIQ9rmeVUnbU')]) {
                              sh 'docker login -u paradax -p ${dckr_pat_Fy1n8Guloriu8eUXIQ9rmeVUnbU}'
                           }
                           sh 'docker push spring-app:latest'
                          }
                      }
                  }


        stage('Run Spring && MySQL Containers') {
                      steps {
                          script {
                            sh 'docker-compose up -d'
                          }
                      }
                  }



}


}