# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-slim
MAINTAINER emmanuel.gaviria

# Crear un directorio para la aplicación
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/customerservice-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación estará escuchando
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]