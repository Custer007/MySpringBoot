FROM eclipse-temurin:21-jre-alpine

#自定义容器内工作目录
WORKDIR /opt/myboot

COPY target/*.jar mySpringntBoot.jar

#日志输出会落到容器内 /opt/springboot_app/logs
ENV TZ=Asia/Shanghai

EXPOSE 8080

ENTRYPOINT ["java","-jar","mySpringntBoot.jar"]