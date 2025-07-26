#CODE BY CHATGPT
# Etapa 1: build da aplicação
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build

# Define o diretório de trabalho no container de build
WORKDIR /app

# Copia todos os arquivos do projeto (código-fonte, pom.xml etc.)
COPY . .

# Roda o build com Maven (sem os testes, para ser mais rápido)
RUN mvn clean package -DskipTests


# Etapa 2: imagem final mais leve com apenas o JAR
FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho no container final
WORKDIR /app

# Copia o JAR da etapa de build para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta da aplicação (mude se sua aplicação usar outra)
EXPOSE 8080

# Comando para iniciar sua aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]