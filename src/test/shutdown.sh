#!/bin/bash
pid=`ps -ef|grep snolfMaster|grep -v grep|awk '{print $2}'`
if [ $pid ]
then
kill -9 $pid