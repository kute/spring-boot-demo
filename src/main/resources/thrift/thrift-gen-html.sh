#!/bin/bash

local_dir=`pwd`;

html_dir=$local_dir"/html";

echo $html_dir

handle(){

    output_html_dir=$html_dir
    if [ $2x != ""x ];then
        output_html_dir=$2
    fi

    if [ ! -d $output_html_dir ];then
        mkdir $output_html_dir;
    fi

    ##step1:为非service文件生成文档

    for file in `ls $1`;
    do
        if [ -f $1"/"$file ]
        then
            if [ "${file##*.}" = "thrift" ]
            then
                ##如果不是service文件则生成
                if echo $file | grep  -v "service"
                then
                    echo $file;
                    thrift  -out $output_html_dir --gen html $1"/"$file;
                fi
            fi
        fi
    done

    ##step2:为service文件生成文档
    for file in `ls $1`;
    do
        if [ -f $1"/"$file ]
        then
            if [ "${file##*.}" = "thrift" ]
            then
                ##如果是service文件则生成
                if echo $file | grep -q "service"
                then
                    echo $file;
                    thrift  -out $output_html_dir --gen html $1"/"$file;
                fi
            fi
        fi
    done

#    for file in `ls $1`;
#    do
#        if [ -d $1"/"$file ]
#        then
#            echo $2
##            echo $2
##            if [ "$2" = "" ];then
##                handle $1"/"$file $html_dir"/"$file
##            else
##                handle $1"/"$file $2"/"$file
##            fi
##
#        fi
#    done
}

handle $local_dir;