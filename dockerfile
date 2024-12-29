# Usar a imagem do OpenJDK
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests

# Definir o diretório de trabalho no contêiner
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copiar o arquivo JAR gerado para o contêiner
COPY target/teamfrank-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta definida no application.properties
EXPOSE 8081

# Comando para rodar o projeto
ENTRYPOINT ["java", "-jar", "app.jar"]
