#!/bin/bash
#Usage bash build.sh package.module
package=$1
module=$2

#Create Voice directory
mkdir -p /home/duncan/duncan_voice/
mkdir -p /home/duncan/duncan_assets/

echo "Build Started for $package.$module"
java -cp target/duncan-1.0-SNAPSHOT.jar $package.$module

#mvn install:install-file -Dfile=dependencies/json-simple-1.1.jar -DgroupId=com.googlecode.json-simple -DartifactId=json-simple -Dversion=1.1 -Dpackaging=jar
