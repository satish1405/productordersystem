FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/productordersystem-0.0.1-SNAPSHOT.jar /app/productordersystem-0.0.1-SNAPSHOT.jar

EXPOSE 8080


ENTRYPOINT ["java" ,"-jar","productordersystem-0.0.1-SNAPSHOT.jar"]