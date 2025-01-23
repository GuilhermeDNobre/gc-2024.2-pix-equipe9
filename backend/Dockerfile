# Etapa 1: Build
FROM gradle:8.11.1-jdk17 AS BUILD
WORKDIR /usr/app/

# Copia os arquivos necessários para o build
COPY build.gradle settings.gradle /usr/app/
COPY src /usr/app/src

# Realiza o build da aplicação
RUN gradle clean build -x test

# Etapa 2: Runtime
FROM eclipse-temurin:17-jre AS RUNTIME

# Variáveis para configuração do aplicativo
ENV APP_HOME=/usr/app/
ENV JAR_NAME=app.jar

WORKDIR $APP_HOME

# Copia o arquivo JAR gerado na etapa de build
COPY --from=BUILD /usr/app/build/libs/*.jar $JAR_NAME

# Expondo a porta da aplicação
EXPOSE 8080

# Comando de entrada configurável
ENTRYPOINT ["java", "-jar", "app.jar"]
