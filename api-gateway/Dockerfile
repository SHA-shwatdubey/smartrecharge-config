FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/api-gateway-1.0.0.jar app.jar
EXPOSE 8062
ENTRYPOINT ["java","-jar","/app/app.jar"]

