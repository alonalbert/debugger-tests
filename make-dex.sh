#!/bin/bash

args=""
i=1
for p in "$@"; do
  if [[ -d $p ]]; then
    jar=/tmp/jar-$$-$i.jar
    i=$((i+1))
    (cd "$p" && jar cf "$jar" .)
    args="$args $jar"
  else
    args="$args $p"
  fi
done

java -cp  ~/src/studio-main/prebuilts/r8/r8.jar com.android.tools.r8.D8 --output ~/tmp/dex-file.jar --min-api 30 $args

rm /tmp/jar-$$-*.jar