FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/ms-produtos-0.0.1-SNAPSHOT.jar msprodutos.jar
ENTRYPOINT ["java", "-jar", "/msprodutos.jar"]