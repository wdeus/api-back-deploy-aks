FROM maven:3.8.8-eclipse-temurin-17 AS build

# Definindo variáveis de ambiente no Dockerfile
ENV DB_URL=jdbc:postgresql://bd-pixel.postgres.database.azure.com:5432/postgres
ENV DB_USERNAME=pixel
ENV DB_PASSWORD=NeymarJr2024

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
