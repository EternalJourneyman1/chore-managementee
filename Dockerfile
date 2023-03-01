FROM openjdk:11-jre-slim

COPY build/libs/chore-management-0.0.1.jar /app/chore-management.jar

WORKDIR /app

RUN chmod +x chore-management.jar

# Start the application
CMD ["java", "-jar", "chore-management.jar"]
