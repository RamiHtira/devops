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
                git branch: 'RaefAifia', 
                url: 'https://github.com/RamiHtira/devops.git'
            }
        }
        stage('Building JAR') {
            steps {
                echo 'Building with Maven'
                sh 'mvn clean install -DskipTests'
            }
        }
        
    }
}