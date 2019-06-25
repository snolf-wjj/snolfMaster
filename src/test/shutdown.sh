#!/bin/sh
pid=`ps -ef|grep snolfMaster|grep -v grep|awk '{print $2}'`
if test -z "$pid";then
   echo "snolfMaster 工程未启动！"
else
  kill -9 $pid
  echo "snolfMaster 工程进程$pid 关闭成功！"
fi