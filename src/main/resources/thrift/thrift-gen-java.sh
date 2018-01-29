#!/bin/bash

local_dir=`pwd`;
java_dir=$local_dir"/gen-java";

handle(){
    for file in `ls $1`
    do
        if [ -f $1"/"$file ]
        then
            if [ "${file##*.}" = "thrift" ]
            then
                echo $file;
                thrift -out $java_dir -r --gen java $1"/"$file;
            fi
        elif [ -d $1"/"$file ]
        then
            handle $1"/"$file;
        fi
    done
}


##判断是否存在java临时目录，不存在则创建
if [ ! -d $java_dir ]
then
        mkdir $java_dir;
fi


##执行全量生成java文件
handle $local_dir;


##复制java文件到指定位置
if [ -d $java_dir ]
then
    cp -r $java_dir/*  $java_dir/../../java;
    rm -r $java_dir;
fi