# Stage 1: Build the JAR using Maven and Java 21
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application skipping tests (database not available during image build)
RUN mvn clean package -DskipTests

# Stage 2: Lightweight runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Render assigns dynamic port via $PORT
ENV PORT=8080
EXPOSE 8080

# Limit JVM heap memory to fit comfortably inside Render's 512 MB free tier
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Xmx350m", "-Xms200m", "-jar", "app.jar"]
