pipeline {
    agent any
    tools {
    	maven 'M2_HOME'
    }
    stages {

        stage('Checkout GIT ') {
            steps {
                echo 'Pulliing ...';
                git branch: 'rami', url: 'https://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@github.com/RamiHtira/devops.git'            }

        }

	   stage('compiler') {
             		steps {
               		sh 'mvn compile'
             		}
           	}
       	    stage('Build') {
             		steps {
               		sh 'mvn -B -DskipTests clean package'
             		}
           	}

               stage('Testing maven') {
       		    steps {
       		    sh """mvn -version"""
       	        }
       	    }




       	stage("TEST JUNIT"){
       		steps{
       		 sh'mvn test -DskipTests '
       		}

       	}

            stage("DockerLogin") {
                       steps {
                       sh 'docker login -u rami2022 -p Rami19981998'
                       }
                       }
             stage('Docker Image Build ') {
       		    steps {
       		      script{
       			    sh 'docker build -t rami2022/achat:latest .'
       		    }
       		}
       		}
       		stage('Docker Image Push ') {
                   steps {
                   script {
       		    sh 'docker push rami2022/achat:latest'
       		    }
       		    }
          	}
//          	 stage("DockerBuild") {
//                        steps {
//                        sh '''cd crud-tuto-front
//                         docker build -t rami2022/achatfront .'''
//                        }
//                        }



//                      stage("DockerPush") {
//                         steps {
//                         sh 'docker push rami2022/achatfront'
//                        }
//                        }
                       stage("Docker-Compose") {
                        steps {
                        sh 'docker-compose up'
                       }
                       }




           }

       }
