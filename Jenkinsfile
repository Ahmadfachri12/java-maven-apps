pipeline {
    agent any

    tools {
        maven 'maven-app'
    }

    stages {
        stage('Build jar') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Test') {
            steps {
                script {
                    echo "Testing the application..."
                    echo "Executing pipeline for branch ${BRANCH_NAME}"
                }
            }
        }

        stage('Build Image') {
            when {
                expression { BRANCH_NAME == 'main' }
            }
            steps {
                script {
                    echo "Building the Docker image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credential', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t ahmdfhri/demo-app:jma-2.0 .'
                        sh "echo \$PASS | docker login -u \$USER --password-stdin"
                        sh 'docker push ahmdfhri/demo-app:jma-2.0'
                    }
                }
            }
        }

        stage('Deploy') {
            when {
                expression { BRANCH_NAME == 'main' }
            }
            steps {
                script {
                    echo "Deploying the application..."
                }
            }
        }
    }
}
