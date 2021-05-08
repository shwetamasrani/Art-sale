pipeline {
  environment{
        dockerImageName1 = ""
        dockerImageName2 = ""

    }
  agent any
    
    
  stages {
        
    stage('Git Pull') {
      steps {
        git branch: 'main', url: 'https://github.com/Goforkanchan/Art-sale-1.git'
      }
    }
    
    stage('Build'){
        steps{
            sh 'mvn clean install'
            sh 'cd src/main/webapp/art-sale && npm install'
        }
    }
     
    stage('Build Docker Images'){
        steps{
            script{
                dockerImageName1 = docker.build "kanchanmahajan67/spe_backend_springboot_wofrontend:latest"
                dockerImageName2 = docker.build("kanchanmahajan67/spe_frontend_react_backwofront:latest", "src/main/webapp/art-sale/")
            }
        }
    }
    stage('Push Docker Image') {
            steps {
                script{
                    docker.withRegistry("", "docker-creds"){
                        dockerImageName1.push()
                        dockerImageName2.push()
                        
                    }
                
                }
            }
        }
 
    }    

   
     
    
            
  }
}
