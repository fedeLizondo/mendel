# Usar la imagen oficial de OpenJDK 21 como base
FROM maven:3.9.9-amazoncorretto-21 AS build

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo pom.xml o build.gradle (según el sistema de construcción que uses)
COPY pom.xml .
COPY mvnw .
# COPY .mvn .

# Dar permisos de ejecución a mvnw
RUN chmod +x ./mvnw

RUN mvn wrapper:wrapper

#With Specific Version
RUN mvn wrapper:wrapper -Dmaven=MAVEN_VERSION
#Example
RUN mvn wrapper:wrapper -Dmaven=3.9.9

# Si usas Maven, puedes agregar las dependencias
RUN ./mvnw dependency:go-offline -B

# Copiar todo el código fuente de la aplicación
COPY src ./src

# Construir el archivo JAR de la aplicación usando Maven (ajusta si usas Gradle)
RUN ./mvnw clean package -DskipTests

# Fase final: Usar una imagen más liviana de JRE para ejecutar la aplicación
FROM openjdk:21-ea-21-oraclelinux8

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR del contenedor anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto en el que la aplicación Spring Boot escuchará
EXPOSE 8080

# Definir el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]