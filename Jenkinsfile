pipeline {
   agent any
   tools {
      maven 'M2_HOME'
  }
  environment {
       DOCKERHUB_CREDENTIALS = credentials('kairi-dockerhub')
	}
  stages {
	
     
            stage('Get Project from Github') {
            steps {
                   echo 'Getting Project from Git' 
                git branch: 'khairi', url: 'https://github.com/RamiHtira/devops.git',
                credentialsId:'git_access'
                
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
        -Dsonar.password=khairi"
  
  
               }
     }
	
     stage("JUNIT/MOCKITO"){
	steps{
	    sh'mvn test'
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
	   nexusUrl: '192.168.1.139:8081/repository/maven-releases', 
           nexusVersion: 'nexus3',
	   protocol: 'http',
  	   repository: 'DeployementRepo',
	   version: '1.0'
        }
     }
     
     /*-----------------
    stage("Build docker image") {
        steps{
	   sh 'chown 666 /var/run/docker.sock'	
           sh 'docker build -t $DOCKERHUB_CREDENTIALS/achat .'
        }
        } 
     
          stage("Publish  image to docker hub") {
        steps{
         	sh 'docker login -ubahatoumi -p Baha2012'
            	sh  'docker push toumibaha/tp_achat_project-1.0:latest'
              }
		  
        }
	  
         stage('DOCKER COMPOSE') {
                 steps {
		      
                      sh 'docker-compose up -d --build'
                   }
              }
              */
     /*-----------*/


  }
  post {
    always {
	     
      cleanWs()
	    
	  
    }

  }
}
