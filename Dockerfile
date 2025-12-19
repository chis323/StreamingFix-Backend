FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml mvnw* ./
COPY .mvn .mvn
RUN mvn -B -q dependency:go-offline

COPY src ./src
RUN mvn -B -q clean package -DskipTests


FROM eclipse-temurin:21-jre-jammy
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} app.jar

ENV JAVA_OPTS="-Xms256m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080

USER 1000
ENTRYPOINT ["/bin/sh","-c","java $JAVA_OPTS -jar /app.jar"]
