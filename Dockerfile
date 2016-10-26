FROM java:8
COPY ./target/service.jar /usr/src/app/
WORKDIR /usr/src/app
CMD java -Xmx4096m -jar service.jar

EXPOSE 9349