#!/bin/bash

local_dir=`pwd`;

html_dir=$local_dir"/../html";

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

    for file in `ls $1`;
    do
        if [ -d $1"/"$file ]
        then
            if [ "$2" = "" ];then
                handle $1"/"$file $html_dir"/"$file
            else
                handle $1"/"$file $2"/"$file
            fi

        fi
    done
}

re_gen_css() {
    css_file="style.css"
    if find $html_dir -name $css_file; then
        css_file_full=$(find $html_dir -name $css_file)
        echo "/* Auto-generated CSS for generated Thrift docs */
body{margin:0;font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;font-size:13px;line-height:18px;color:#333333;background-color:#ffffff;}
a{color:#0088cc;text-decoration:none;}
a:hover{color:#005580;text-decoration:underline;}
pre{padding:0 3px 2px;font-family:Menlo,Monaco,Consolas,"Courier New",monospace;font-size:12px;color:#333333;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;}
h3,h4 { padding-top: 0px; margin-top: 0px; }
div.definition { border: 1px solid cornflowerblue; margin: 10px; padding: 10px; }
div.extends { margin: -0.5em 0 1em 5em }
table { border: 1px solid cornflowerblue; border-collapse: collapse; }
td { border: 1px solid cornflowerblue; padding: 1px 6px; vertical-align: top; }
th { border: 1px solid black; background-color: cornflowerblue;
     text-align: left; padding: 1px 6px; }" > $css_file_full
    fi
}

cp_html() {
expect << EOF
    if {[file isdirectory $html_dir] == 1 } {
        spawn scp -P2255 -r $html_dir sankuai@10.4.226.55:/var/www/html/api/doc/$1
        set timeout 3
        expect {
            "*yes/no*" {
                send "yes\n"
                expect "*assword" {send "sankuai\n"}
            }
            "*assword*" {
                send "sankuai\n"
            }
        }
    }
    expect eof
EOF
}

handle $local_dir;
re_gen_css;
cp_html $1;