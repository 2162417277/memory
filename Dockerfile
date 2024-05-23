FROM openjdk:8

EXPOSE 8080
WORKDIR /usr/local/java
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV TZ=Asia/Shanghai
ARG JAR_FILE
ADD ${JAR_FILE} ./memory.jar

ENTRYPOINT ["java","-jar","/usr/local/java/memory.jar","--spring.profiles.active=prod","/usr/local/java/memory.log"]