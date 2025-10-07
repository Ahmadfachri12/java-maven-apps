package com.example

class Docker implements Serializable {
    def script
    Docker(script) { this.script = script }

    def login() {
        script.withCredentials([script.usernamePassword(
            credentialsId: 'dockerhub-creds',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )]) {
            script.sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
        }
    }

    def buildImage(String imageName) {
        script.sh "docker build -t ${imageName} ."
    }

    def pushImage(String imageName) {
        script.sh "docker push ${imageName}"
    }
}