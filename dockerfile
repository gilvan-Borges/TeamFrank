# Dockerfile
# Usar a imagem do Maven com JDK para build
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app

# Copiar o arquivo pom.xml e baixar dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o restante do projeto e buildar o JAR
COPY . .
RUN chmod +x mvnw
RUN ./mvnw -B clean package -DskipTests

# Imagem final com OpenJDK
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copiar o JAR gerado para o contêiner final
COPY --from=builder /app/target/teamfrank-0.0.1-SNAPSHOT.jar /app/target/teamfrank-0.0.1-SNAPSHOT.jar

# Expor a porta configurada
EXPOSE 8081

# Comando para rodar a aplicação
CMD ["java", "-jar", "/app/target/teamfrank-0.0.1-SNAPSHOT.jar"]