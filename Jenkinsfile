pipeline {
    agent any
    tools {
        jdk 'jdk17'
        maven 'M3'
    }
    environment {
        DOCKER_REGISTRY = 'crpi-5mt3q7j246hdfcod.cn-guangzhou.personal.cr.aliyuncs.com/springboot-custer'
        DOCKER_CRED = 'docker-repo-cred'
        IMAGE_NAME = 'demo-app'
        IMAGE_TAG = "${env.BUILD_NUMBER}"
        SSH_DEPLOY_ID = 'deploy-server'
    }
    stages {
        stage('Maven编译打包') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('构建推送Docker镜像') {
            steps {
                withCredentials([usernamePassword(credentialsId:"${DOCKER_CRED}",usernameVariable:'DOCKER_USER',passwordVariable:'DOCKER_PWD')]){
                    sh '''
docker login crpi-5mt3q7j246hdfcod.cn-guangzhou.personal.cr.aliyuncs.com -u ${DOCKER_USER} -p ${DOCKER_PWD}
docker build -t ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG} .
docker push ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}
docker tag ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_REGISTRY}/${IMAGE_NAME}:latest
docker push ${DOCKER_REGISTRY}/${IMAGE_NAME}:latest
'''
                }
            }
        }
        stage('SSH远程部署应用') {
            steps {
                withCredentials([string(credentialsId: 'docker-repo-pwd', variable: 'ACR_PWD')]) {
                    sshPublisher(publishers: [sshPublisherDesc(
                                configName: "deploy-server",
                                transfers: [sshTransfer(
                                        sourceFiles: '',
                                        execCommand: '''
docker login crpi-5mt3q7j246hdfcod.cn-guangzhou.personal.cr.aliyuncs.com -u nick9992324307 -p ''' + "\"${ACR_PWD}\"" + '''
docker rm -f my-springboot
docker pull crpi-5mt3q7j246hdfcod.cn-guangzhou.personal.cr.aliyuncs.com/springboot-custer/demo-app:latest
docker run -d --name my-springboot -p 8081:8083 crpi-5mt3q7j246hdfcod.cn-guangzhou.personal.cr.aliyuncs.com/springboot-custer/demo-app:latest
'''
                                    )]
                            )])
                }
            }
        }
    }
    post {
        success { echo "✅流水线构建完成" }
        failure { echo "❌流水线失败，查看控制台日志" }
        always { cleanWs() }
    }
}