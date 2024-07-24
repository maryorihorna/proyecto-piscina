# build
FROM maven:3.9.8-amazoncorretto-17-al2023

WORKDIR /usr/src/app

COPY pom.xml .

RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

COPY . .
RUN mvn -B -e -U clean install
RUN mvn -B -e -o -T 1C verify

# package without maven
FROM amazoncorretto:17.0.12-alpine3.20

COPY --from=0 /usr/src/app/target/*.jar ./app.jar

EXPOSE 50

CMD ["java", "-jar", "app.jar"]