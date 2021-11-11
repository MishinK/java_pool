#!/bin/sh
cd ChaseLogic && mvn clean install
cd ../Game && mvn clean install 
cd ..
java -jar Game/target/Game.jar --enemiesCount=5 --wallsCount=5 --size=10 --profile=production