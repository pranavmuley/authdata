pipeline {
  agent any  
  stages {
    stage('Build') {
      steps {        
          bat './mvnw clean verify'        
      }
    }
  }
}
