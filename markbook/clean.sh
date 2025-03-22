#!/bin/bash

rm -rf ~/javaspace/mk/markbook-0.0.1-SNAPSHOT.jar

mv target/markbook-0.0.1-SNAPSHOT.jar ~/javaspace/mk/

rm -rf ./src/main/resources/application.yml

mvn clean

java -jar ~/javaspace/mk/markbook-0.0.1-SNAPSHOT.jar
