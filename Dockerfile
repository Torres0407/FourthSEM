# your docker content
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN javac HangmanCMD.java
CMD ["java", "HangmanCMD"]
