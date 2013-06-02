#!/bin/bash
if [ $# -ne 4 ]; then
   echo "JSimil - Process"
   echo ""
   echo "Usage: $0 [source_file] [profile] [number_results] [dest_dir]"
   echo ""
   echo "This script performs a processing and generates HTML results."
   exit 127
fi
java -Xms128M -Xmx1618M -jar JSimilCLI.jar -c default.jcf -b $1 -p $2 -u $3 -e $4 -f -i -y -r
