pipeline {
  agent any
  stages {
    stage('SonarQube') {
      steps {
        bat 'mvn sonar:sonar'
      }
    }
    stage('Build') {
      steps {
        bat 'mvn install -DskipTests=true'
      }
    }
    stage('Test') {
      steps {
        bat 'mvn test'
      }
    }
  }
}