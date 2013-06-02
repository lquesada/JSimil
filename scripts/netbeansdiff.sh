#!/bin/bash
cd `dirname $0`
if [ "$1" == "--" ] ; then
  shift;
elif [ "$1" == "-" ]; then
  shift;
fi
if [ $# -ne 5 ]; then
   exit 127
fi
mkdir -p $5
version=`java -jar JSimilCLI.jar 2>&1 | grep ^JSimil\ | cut -f 2 -d ' '`
md5sum1=`md5sum "$1" | cut -f 1 -d ' '` 
md5sum2=`md5sum "$2" | cut -f 1 -d ' '` 
md5sump=`md5sum "$3" | cut -f 1 -d ' '` 
nom=$5/JSimil.diff.$md5sum1.$md5sum2.$md5sump.$4.$version
non=$5/JSimil.diff.$md5sum2.$md5sum1.$md5sump.$4.$version
if [ -f $nom ]; then
  cat $nom
  exit 0
fi;  
rm -rf $5/temp $5/tempres
mkdir $5/temp
mkdir $5/temp/old
mkdir $5/temp/new
rm -f $5/temp/err
cp -rf $1 $5/temp/old/jscold.java
javac $5/temp/old/jscold.java &> $5/temp/err
nombre=`cat $5/temp/err | grep is\ public,\ should\ be\ declared | tr ' ' '\n' | tail -n 1`
mv -f $5/temp/old/jscold.java $5/temp/old/$nombre
rm -f $5/temp/err
cp -rf $2 $5/temp/new/jscnew.java
javac $5/temp/new/jscnew.java &> $5/temp/err
nombre=`cat $5/temp/err | grep is\ public,\ should\ be\ declared | tr ' ' '\n' | tail -n 1`
mv -f $5/temp/new/jscnew.java $5/temp/new/$nombre
(java -Xms128M -Xmx1618M -jar JSimilCLI.jar -c default.jcf -t $5/temp -p $3 --diff-simil $4 -k $5/tempres -f -i -y -r) 2>&1 > /dev/null
cp $5/tempres/old-new.diff $nom
cp $5/tempres/new-old.diff $non
cat $nom
rm -rf $5/temp $5/tempres
