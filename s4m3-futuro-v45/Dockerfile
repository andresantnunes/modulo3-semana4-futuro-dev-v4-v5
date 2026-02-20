# Fase 1 - build
# Cria um executor linux, com todos os itens de Java
# Tem uma lable/titulo de build
FROM eclipse-temurin:25-jdk-alpine AS build

# Cria uma pasta para trabalharmos
# WORKDIR - local de trabalho, é a pasta que vamos utilizar
WORKDIR /app

# app/.mvn
# app/mvnw, app/pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# app/src/ todos os arquivos do projeto
COPY src ./src

# Muda a permissão do mvnw para ser executável
RUN chmod +x mvnw

# Executa comandos Linux
# clean package
RUN ./mvnw clean package -DskipTests

# Fase2 - run
# Cria um segundo executor, apenas com o JRE, runtime do Java
# Menor que o JDK
FROM eclipse-temurin:25-jre-alpine

# Comandos linux para permitir a execução dos comando sem root no linux
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Extraindo apenas o jar da pasta app
COPY --from=build /app/target/*.jar app.jar

# Expõem um porta dentro do docker
# Só podemos export uma porta para o computador por comandos ou Docker-compose
EXPOSE 8080

# Comando para rodar o jar
ENTRYPOINT ["java", "-jar", "app.jar"]
