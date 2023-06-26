#!/bin/bash
app='storyline-1.0-SNAPSHOT.jar'
cmd=$1
pid=`ps -ef|grep java|grep $app|awk '{print $2}'`
pid=`ps -ef|grep java|grep storyline-1.0-SNAPSHOT.jar|awk '{print $2}'`

echo "$pid will be killed after 3 seconds!"
kill -9 $pid
sleep 3
nohup java -jar $app &
tail -f nohup.out
