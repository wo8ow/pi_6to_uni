# Etapa de Compilación (Build)
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Empaquetar saltando tests para agilizar (en prod real se deberían correr)
RUN mvn clean package -DskipTests

# Etapa de Ejecución (Run)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos el jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]