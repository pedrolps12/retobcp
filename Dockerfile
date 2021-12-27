FROM openjdk:11.0.13-jdk-slim
USER root
COPY  ./target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 9020