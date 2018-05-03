#!/bin/bash

NAME=$1
if [ "$NAME" == "" ]; then
  NAME=`date +%Y-%m-%d`
fi
mkdir $NAME
cd $NAME
cp -r ../template/* .

revision=`git rev-parse HEAD`

for p in b c d e f g h i j k; do
  mkdir -p src/main/java/$p
  cp src/main/java/a/Solution.java src/main/java/$p/Solution.java
  sed -i "s/package a;/package $p;/" src/main/java/$p/Solution.java
  sed -i "s/__REVISION__/$revision/" src/main/java/$p/Solution.java

  P=$(echo $p | tr '[a-z]' '[A-Z'])
  cp src/test/resources/A-sample.txt src/test/resources/$P-sample.txt
done

echo New directory: $NAME

