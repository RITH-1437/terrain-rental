pipeline {
agent any

```
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
            echo 'Running Ansible deployment stage'
            bat 'type playbook.yml'
            bat 'type inventory.ini'
        }
    }
}

post {
    success {
        emailext(
            subject: 'Build Success',
            body: 'Terrain Rental pipeline completed successfully.',
            to: 'srengty@gmail.com'
        )
    }

    failure {
        emailext(
            subject: 'Build Failed',
            body: 'Terrain Rental pipeline failed.',
            to: 'srengty@gmail.com'
        )
    }
}
```

}
