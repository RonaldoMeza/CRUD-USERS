# --- ETAPA DE CONSTRUCCIÓN ---
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copiar archivos de Maven para aprovechar la caché de capas
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copiar el código fuente y compilar
COPY src src
RUN ./mvnw clean package -DskipTests

# --- ETAPA DE EJECUCIÓN ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiar solo el JAR generado desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto (Render lo detectará o inyectará PORT)
EXPOSE 8080

# Ejecutar con configuraciones óptimas para contenedores
ENTRYPOINT ["java", "-jar", "app.jar"]