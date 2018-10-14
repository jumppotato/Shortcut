#!/usr/bin/env bash

user=root
ip=39.106.53.243
pem_path=/Users/wangwenxuan/Documents/my/mac-13.4.pem
jar_name=Shortcut-1.0-SNAPSHOT.jar

function info(){
	local dateTime=`date +%c`
    echo 'Deploy '$dateTime' [INFO] : '$*
}

function err(){
	local dateTime=`date +%c`
    echo 'Deploy '$dateTime' [ERROR] : '$*
}

function check(){
	if [ $? -ne 0 ]; then
		err $*
		exit 1
	fi
}

base_path=$(cd `dirname $0`; pwd)

cd ${base_path}/../
mvn install
check 'mvn deploy failed'
cd -

scp -i ${pem_path} ${base_path}/../target/${jar_name} ${user}@${ip}:/root/servers/
check 'scp failed'

ssh ${user}@${ip} -i ${pem_path} -tt << abc
    pwd
    work_pid=\$(ps -ef|grep Shortcut|grep java|awk '{print \$2}')
    if [[ "\${work_pid}" != "" ]];then
        kill -9 \${work_pid}
    fi
    java -jar /root/servers/${jar_name} &
    exit
abc
