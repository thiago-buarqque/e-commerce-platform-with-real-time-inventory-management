# Etapa 1: Build da aplicação com Maven 3.9.9 e JDK 21
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
# Copia o arquivo de configuração do Maven
COPY pom.xml .
# Copia o código-fonte da aplicação
COPY src ./src
# Compila a aplicação e gera o JAR (pula os testes para acelerar o build, se desejar)
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final para rodar a aplicação com Java 21
FROM eclipse-temurin:21-jdk
WORKDIR /app
# Copia o JAR gerado na etapa de build para a imagem final
COPY --from=build /app/target/*.jar app.jar
# Expõe a porta 8080 (opcional, se sua aplicação rodar nessa porta)
EXPOSE 8080
# Comando que inicia a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
