#!/bin/bash
if [ $# -ne 2 ]; then
   echo "JSimil - Load a battery"
   echo ""
   echo "Usage: $0 <source_dir> <target_file>"
   exit 127
fi
java -Xms128M -Xmx1618M -jar JSimilCLI.jar -c default.jcf -t $1 -g $2 -v
