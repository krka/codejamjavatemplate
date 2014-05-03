#!/bin/bash

# It may be useful to put a user_config.py in the template directory.

NAME=$1
if [ "$NAME" == "" ]; then
  NAME=`date +%Y-%m-%d`
fi
mkdir $NAME
cd $NAME
cp -r ../template/* .
unzip ../codejam-commandline*.zip > /dev/null
rm -rf codejam-commandline*/source
mv codejam-commandline*/* .
rmdir codejam-commandline*
if [ -f user_config.py ]; then
  mv user_config.py config
fi

revision=`git rev-parse HEAD`

for p in A B C D E F G H I J K; do
  cp source/Template.java source/$p.java
  sed -i "s/Template/$p/" source/$p.java
  sed -i "s/__REVISION__/$revision/" source/$p.java
done

echo New directory: $NAME

