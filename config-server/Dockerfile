FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/config-server-1.0.0.jar app.jar
EXPOSE 8060
ENTRYPOINT ["java","-jar","/app/app.jar"]

