#!/bin/bash
tpid=`cat tpid | awk '{print $1}'`
tpid=`ps -aef | grep $tpid | awk '{print $2}' |grep $tpid`
if [ ${tpid} ]; then 
        echo 'kill process...'
        kill -9 $tpid
fi