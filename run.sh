#!/bin/bash

echo "Now building"
mvn clean install
echo "Now running the program"
echo
echo
java -cp target/codingexercise-0.0.1-SNAPSHOT.jar com.trinetx.codingexercise.MazeSolver map.xml scenario.txt
