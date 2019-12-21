FROM maven:3.6.2-jdk-8-slim AS build  
COPY ./src /build/
WORKDIR /build/
RUN mvn clean package

FROM openjdk:8-jre-alpine
COPY --from=build /build/backend/target/backend-0.0.1-SNAPSHOT.jar /app/qlive.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","/app/qlive.jar"]
