
FROM openjdk:11

WORKDIR /app
COPY . .

RUN ["javac", "Main.java"]

CMD ["java", "-cp", ".:mysql-connector-j-8.3.0.jar", "Main"]
