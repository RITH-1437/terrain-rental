pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/RITH-1437/terrain-rental.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvnw.cmd clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvnw.cmd test'
            }
        }

        stage('Deploy') {
            steps {
                bat 'wsl ansible-playbook -i inventory.ini playbook.yml'
            }
        }
    }

    post {
        failure {
            emailext(
                subject: "Build Failed",
                body: "Jenkins build failed.",
                to: "srengty@gmail.com"
            )
        }
    }
}