FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/processing-service-1.0.0.jar app.jar
EXPOSE 8065
ENTRYPOINT ["java","-jar","/app/app.jar"]

