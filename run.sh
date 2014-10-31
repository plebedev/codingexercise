#!/bin/bash

if [ ! $# == 2 ]; then
  echo "Usage: $0 map.xml scenario.txt"
  exit
fi

echo "Now building"
mvn clean install
echo "Now running the program"
echo
echo
java -cp target/codingexercise-0.0.1-SNAPSHOT.jar com.trinetx.codingexercise.MazeSolver $1 $2
