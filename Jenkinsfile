pipeline {
  agent any  
  stages {
    stage('Build') {
      steps {
        sh '''
        echo "PATH = ${PATH}"
        echo "M2_HOME = ${M2_HOME}"
        '''
        dir("project_templates/java_project_template"){
          sh './mvnw clean verify'
        }
      }
    }
  }
}
