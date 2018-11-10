#!/bin/bash
set -ue

cp -R res bin
javac -d bin/ -cp src src/Main.java
java -cp bin Main
