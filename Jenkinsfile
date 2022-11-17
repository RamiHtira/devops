pipeline {
    agent any
    tools {
    	maven 'M2_HOME'
    }
     environment {
        DOCKERHUB_CREDENTIALS = credentials('4ba1fe15-cf5c-4117-8423-1275412b0318')
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




//        	stage ('Maven Test Sonar') {
//                     steps {
//                         sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sousou.123'
//                     }
//
//                 }

                stage ('Maven Test JUnit') {
                    steps {
                        echo 'mvn test'
                    }
                }
//                 stage ('Maven Deploy Nexus') {
//                     steps {
//                         sh'mvn clean deploy -Dmaven.test.skip=true -Dresume=false'
//                     }
//                 }


        		stage('Build image') {

        			steps {
        				sh 'docker build -t rami2022/devops:add'
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
//         			    sh 'docker tag spring_boot_image:first_img rami2022/devops_project:add'
//         				sh 'docker push rami2022/devops_project:add'
//         			}
//         		}

        		stage('Docker-Compose Up') {

        			steps {
        				sh 'docker-compose up'
        			}
        		}

//         		stage('Docker-Compose Down') {
//
//         			steps {
//         				sh 'docker-compose down'
//         			}
//         		}

        	}

        /*	post {
        		always {
        			sh 'docker logout'
        		}
        	}*/

        }