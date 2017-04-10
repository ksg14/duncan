#!/bin/bash
package=$1
module=$2
echo "Build Started for $package.$module"
java -cp target/duncan-1.0-SNAPSHOT.jar $package.$module
