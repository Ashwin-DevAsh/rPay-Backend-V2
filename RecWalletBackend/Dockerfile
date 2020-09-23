FROM openjdk:14-jdk-alpine
COPY . .
RUN chmod +x gradlew
EXPOSE 8080
CMD ./gradlew bootjar && java -jar /build/libs/RecWalletBackend-0.0.1-SNAPSHOT.jar