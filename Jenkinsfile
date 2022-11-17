pipeline {
   agent any
   tools {
      maven 'M2_HOME'
  }
  environment {
       DOCKERHUB_CREDENTIALS = credentials('paradax-dockerhub')
	}
  stages {


            stage('Get Project from Github') {
            steps {
                   echo 'Getting Project from Git'
                git branch: 'borhen', url: 'https://github.com/RamiHtira/devops.git'

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

     stage("JUNIT/MOCKITO"){
	steps{
	    sh'mvn test'
	     }
	}

    /*  stage("nexus") {
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
     } */

     /*-----------------*/
    stage("Build docker image") {
        steps{

           sh 'docker build -t target/tp_achat_project-1.0 .'
        }
        }

          stage("Publish  image to docker hub") {
        steps{
         	sh 'docker login paradax -p dckr_pat_leVtk0k0LHlYhQJBctXFZVaz9qM'
            	sh  'docker push target/tp_achat_project-1.0:latest'
              }

        }

         stage('DOCKER COMPOSE') {
                 steps {

                      sh 'docker-compose up -d --build'
                   }
              }

     /*-----------*/


  }
  post {
    always {

      cleanWs()


    }

  }
}