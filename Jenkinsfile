pipeline {
agent any
    environment {
        registry="samarrmdhn/devopsimage"
        registryCredential='samarrmdhn'
        dockerImage =''
    }
stages{
stage('clone and clean repo'){
steps {
bat "git clone -b samar_Branch https://github.com/Samar-web-prog/Devops.git"
bat "mvn clean -f Devops"
bat "mvn install -f Devops"

}

}
stage('Test'){

steps{ bat "mvn test -f Devops"
}
   
}

stage('Build,Deploy(nexus),Sonar'){
steps {
bat "mvn package -f Devops"
//bat "mvn deploy -f Devops"
//bat "mvn sonar:sonar -f Devops"
}
}

 stage('Building image') {
      steps{
          dir("Devops") {
        script {
            DOCKER_BUILDKIT=0
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
      }
    }
    stage('Push Docker Image') {
      steps{
             dir("Devops") {
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
          }}
        }
      }
    }
   stage('Remove Unused docker image') {
      steps{
          
          bat "docker rmi $registry:$BUILD_NUMBER"
      }
    } 





}
}
