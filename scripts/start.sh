#!/bin/bash
rm -f tpid
nohup java -jar /home/www/java/otome-1.0.jar --spring.profiles.active=dev > otome.log 2>&1 &
echo $! > tpid
echo start success..
