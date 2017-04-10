#!/bin/bash
module=$1
echo "Build Started for $module"
java -cp target/duncan-1.0-SNAPSHOT.jar app.$module
