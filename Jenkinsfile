pipeline {
  agent any
  tools{
    maven 'maven 3'
    jdk 'java 8'
  }
  stages {
    stage('Build') {
      steps {
        sh '''
        echo "PATH = ${PATH}"
        echo "M2_HOME = ${M2_HOME}"
        '''
        dir("project_templates/java_project_template"){
          sh 'mvn clean verify
        }
      }
    }
  }
}
