pipeline{
    agent any

    environment {
       PROJECT_NAME = "puntengopudd-dev"
       BUILD_NAME = "bni-java-project-git"
    }

    stages {
        stage('Triggers Build in Openshift') {
            steps {
                sh "oc start-build ${BUILD_NAME} --from-dir=. --follow -n ${PROJECT_NAME}"
            }
        }

        stage("Deploy to Openshift") {
            steps {
                sh "oc rollout restart deployment/${BUILD_NAME} -n ${PROJECT_NAME}"
            }
        }
    }

    post {
        success {
            echo "Build Succeeded!"
        }
        failure {
            echo "Build Failed!"
        }
    }
}