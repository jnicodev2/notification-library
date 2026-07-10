FROM maven:3.9.8-eclipse-temurin-21

WORKDIR /app

COPY . .

RUN mvn clean compile

CMD ["mvn","exec:java","-Dexec.mainClass=com.jnicodev.notification.demo.Main"]