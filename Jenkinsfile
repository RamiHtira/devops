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
	    }}