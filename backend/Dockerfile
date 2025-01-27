# Etapa 1: Build
FROM gradle:8.11.1-jdk17 AS BUILD
WORKDIR /usr/app/

# Copia todos os arquivos do projeto para o container
COPY . /usr/app/

# Garante que o gradlew seja executável
RUN chmod +x gradlew

# Realiza o build usando o gradlew (não diretamente o gradle)
RUN gradle clean build -x test

# Etapa 2: Runtime
FROM eclipse-temurin:17-jre AS RUNTIME

# Variáveis para configuração do aplicativo
ENV APP_HOME=/usr/app/
ENV JAR_NAME=app.jar

WORKDIR $APP_HOME

# Copia o arquivo JAR gerado na etapa de build
COPY --from=BUILD $APP_HOME/build/libs/*.jar $JAR_NAME

# Expondo a porta da aplicação
EXPOSE 8080

# Comando de entrada configurável
ENTRYPOINT ["java", "-jar", "app.jar"]
