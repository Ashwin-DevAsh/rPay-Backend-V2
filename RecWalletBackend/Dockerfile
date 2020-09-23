FROM openjdk:14-jdk-alpine
COPY . .
RUN chmod +x gradlew
RUN ./gradlew bootjar
EXPOSE 8080
CMD java -jar /build/libs/RecWalletBackend-0.0.1-SNAPSHOT.jar