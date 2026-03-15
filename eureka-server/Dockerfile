FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/eureka-server-1.0.0.jar app.jar
EXPOSE 8061
ENTRYPOINT ["java","-jar","/app/app.jar"]

