pipeline {
    agent any
    tools {
    	maven 'M2_HOME'
    }
    stages {

        stage('Checkout GIT ') {
            steps {
                echo 'Pulliing ...';
                git branch: 'ranim', url: 'https://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@github.com/Akarmous/CI-CD-GladOps.git'            }

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
	    }}