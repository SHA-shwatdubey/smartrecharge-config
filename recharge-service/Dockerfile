FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/recharge-service-1.0.0.jar app.jar
EXPOSE 8064
ENTRYPOINT ["java","-jar","/app/app.jar"]

