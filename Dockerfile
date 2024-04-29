FROM openjdk:22-oracle

WORKDIR /app
COPY target/sumativa2-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_PEDROFALFANFS /app/oracle_wallet
EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]