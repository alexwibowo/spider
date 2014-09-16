#!/bin/sh

BARCODE_SPIDER_HOME=$INSTALL_PATH
JAVA_HOME=$JDKPath

PATH=$JAVA_HOME/bin:$PATH

function buildClasspathFromDirectory(){
        local CLASSPATH=""
        local files=`ls $1/*.jar`
                for file in $files; do
                        CLASSPATH="$CLASSPATH":"$file"
                done
        echo $CLASSPATH
}

CLASSPATH=`buildClasspathFromDirectory $BARCODE_SPIDER_HOME/lib`

# Launch Barcode Spider
java -cp $CLASSPATH org.github.alexwibowo.spider.SpiderLauncher

