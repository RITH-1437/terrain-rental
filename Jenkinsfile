pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/RITH-1437/terrain-rental.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Deploy') {
            steps {
                sh 'ansible-playbook -i inventory.ini playbook.yml'
            }
        }
    }

    post {

        success {
            echo 'Build, Test and Deploy Success'
        }

        failure {
            emailext(
                subject: "Build Failed: ${env.JOB_NAME}",
                body: "Build failed. Please check Jenkins.",
                to: "srengty@gmail.com"
            )
        }
    }
}