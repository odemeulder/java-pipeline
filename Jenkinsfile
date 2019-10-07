pipeline {
  agent { 
    label 'odm-ci-slave' 
  }
  stages {
    stage('Test & Build') {
      steps {
        echo 'Starting Pipeline'
        sh './gradlew clean build'
      }
    }
    stage('Create jar') {
      steps {
        sh './gradlew bootJar'
      }
    }
    stage('Archive') {
      steps {
        archiveArtifacts(artifacts: 'build/libs/odm-java-pipeline-0.1.0.jar', onlyIfSuccessful: true)
      }
    }
    stage('Deploy') {
      steps {
        sh 'ANSIBLE_HOST_KEY_CHECKING=false ansible-playbook -i ansible/inventory ansible/deploy.yml'
      }
    }
    stage('Done') {
      steps {
        echo 'Pipeline done'
      }
    }
  }
}