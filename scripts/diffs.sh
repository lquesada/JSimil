#!/bin/bash
if [ $# -ne 5 ]; then
  echo "JSimil - diff between two targets"
  echo ""
  echo "Usage: $0 [battery] [profile] [similarity_thres] [tempdir]"
  echo ""
  echo "This script processes a battery and generates diff results."
  exit 127
fi
java -Xms128M -Xmx1618M -jar JSimilCLI.jar -c default.jcf -b $1 -p $2 -u $3 --diff-simil $5 -k $4 -f -i -y -r
