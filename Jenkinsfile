pipeline {
    agent any
    parameters {
        string name: 'platformType', trim: false, defaultValue: 'chrome'
        string name: 'TEST_CLASS', trim: false, defaultValue: 'BookFlightTest'
    }
    stages {
        stage('Build Jar') {
            steps {
                echo "clean package and build test jar"
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Run Tests') {
            steps {
                bat "set platformType=\"${env.platformType}\""
                echo "set ${env.platformType} with ${env.TEST_CLASS} test"
                bat "mvn clean test -Dtest=${TEST_CLASS} -DplatformType=${platformType}"
            }
        }
    }
     post {
           always {
               script {
                  allure([
                         includeProperties: false,
                         jdk: '',
                         properties: [],
                         reportBuildPolicy: 'ALWAYS',
                         results: [[path: 'target/allure-results']]
                  ])
               }
           }
     }
}