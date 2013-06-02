#!/bin/bash
if [ "$1" == "--" ] ; then
  shift;
elif [ "$1" == "-" ]; then
  shift;
fi
if [ $# -ne 5 ]; then
   echo "JSimil - diff between two targets"
   echo ""
   echo "Usage: $0 [old] [new] [profile] [similarity_thres] [tempdir]"
   echo ""
   echo "This script processes the two targets and generates diff results."
   echo ""
   echo "Both old and new must be two jar files, java files, or directories."
   exit 127
fi
rm -rf $5/temp $5/tempres
mkdir $5/temp
mkdir $5/temp/old
mkdir $5/temp/new
cp -rf $1 $5/temp/old
cp -rf $2 $5/temp/new
(java -Xms128M -Xmx1618M -jar JSimilCLI.jar -c default.jcf -t $5/temp -p $3 --diff-simil $4 -k $5/tempres -f -i -y -r) &> /dev/null
cat $5/tempres/old-new.diff
rm -rf $5/temp $5/tempres
