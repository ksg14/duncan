#!/bin/bash
#Usage bash build.sh package.module
package=$1
module=$2

#Create Voice directory ***System Dependent***
mkdir -p /home/phiber/Desktop/Work/duncan/duncan/target/voice/

echo "Build Started for $package.$module"
java -cp target/duncan-1.0-SNAPSHOT.jar $package.$module
