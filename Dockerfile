# Build stage: utiliza una imagen de Maven con Amazon Corretto JDK 17
FROM maven:3.9.8-amazoncorretto-17-al2023 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /usr/src/app

# Copia el archivo pom.xml para descargar las dependencias del proyecto
COPY pom.xml .

# Ejecuta el comando Maven para descargar todas las dependencias necesarias en modo offline
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

# Copia todos los archivos del proyecto al contenedor
COPY . .

# Ejecuta la construcción del proyecto y realiza la instalación de los artefactos
RUN mvn -B -e -U clean install

# Verifica la construcción del proyecto utilizando las dependencias en caché
RUN mvn -B -e -o -T 1C verify



# Package stage: utiliza una imagen de Amazon Corretto JDK 17 sobre Alpine Linux
FROM amazoncorretto:17.0.12-alpine3.20

# Argumentos y variables de entorno
ARG DB_URL
ARG DB_USER
ARG DB_PASSWORD
ARG GH_CLIENT_ID
ARG GH_CLIENT_SECRET
ARG GOOGLE_CLIENT_ID
ARG GOOGLE_CLIENT_SECRET

ENV DB_URL=$DB_URL
ENV DB_USER=$DB_USER
ENV DB_PASSWORD=$DB_PASSWORD
ENV GH_CLIENT_ID=$GH_CLIENT_ID
ENV GH_CLIENT_SECRET=$GH_CLIENT_SECRET
ENV GOOGLE_CLIENT_ID=$GOOGLE_CLIENT_ID
ENV GOOGLE_CLIENT_SECRET=$GOOGLE_CLIENT_SECRET

# Copia el archivo JAR generado desde el contenedor de la etapa de construcción
COPY --from=build /usr/src/app/target/*.jar ./app.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 50

# Define el comando por defecto para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]