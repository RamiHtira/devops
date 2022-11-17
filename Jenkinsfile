pipeline {
    agent any
    tools {
    	maven 'M2_HOME'
    }
     environment {
        DOCKERHUB_CREDENTIALS = credentials('paradax-dockerhub')
      }
    stages {

        stage('Checkout GIT ') {
            steps {
                echo 'Pulliing ...';
                git branch: 'borhen', url: 'https://github.com/RamiHtira/devops.git'            }

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




         	stage ('Maven Test Sonar') {
                    steps {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=paradax'
                    }

                }

                stage ('Maven Test JUnit') {
                    steps {
                        echo 'mvn test -DskipTests'
                    }
                }
//                 stage ('Maven Deploy Nexus') {
//                     steps {
//                         sh'mvn clean deploy -Dmaven.test.skip=true -Dresume=false'
//                     }
//                 }


        		stage('Build image') {

        			steps {
        				sh 'docker build -t spring-app:latest .'
        			}
        		}

        		stage('Login docker hub') {

        			steps {

        				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password $DOCKERHUB_CREDENTIALS_PSW'
        			}
        		}

//         		stage('Push image') {
//
//         			steps {
//
//         				sh 'docker push rami2022/devops_project:add'
//         			}
//         		}

        		stage('Docker-Compose Up') {

        			steps {
        				sh 'docker-compose up'
        			}
        		}

        		stage('Docker-Compose Down') {

        			steps {
        				sh 'docker-compose down'
        			}
        		}

        	}

        /*	post {
        		always {
        			sh 'docker logout'
        		}
        	}*/

        }