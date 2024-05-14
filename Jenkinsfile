pipeline{
    agent any
    stages {
        stage('Checkout') {
            steps {
                echo "checkout Git"
                cleanWs()
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/srtthomas/devops-ci']])
            }
        }
    stage('Build') {
        steps {
            echo "build"
            sh 'chmod +x ./backend/gradlew'
            sh './backend/gradlew -p backend test'
            nodejs('NodeJS 20.10.0') {
            sh '''npm install --prefix frontend
                  npm run build --prefix frontend'''
                }
            }
        }
    }
    post {
        always {
            junit '**/test-results/test/*.xml'
            jacoco()
        }
    success {
        echo "Build succeeded!"
        }
    failure {
        echo "Build failed!"
        }
    }
}
