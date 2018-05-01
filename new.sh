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
  mkdir -p source/$p
  cp source/a/Solution.java source/$p/Solution.java
  sed -i "s/package a;/package $p;/" source/$p/Solution.java
  sed -i "s/__REVISION__/$revision/" source/$p/Solution.java
done

echo New directory: $NAME

