#!/bin/sh

BARCODE_SPIDER_HOME=$INSTALL_PATH
JAVA_HOME=$JDKPath

PATH=${JAVA_HOME}/bin:$PATH
CLASSPATH=".:${BARCODE_SPIDER_HOME}/lib/*"

# Launch Barcode Spider
java -cp ${CLASSPATH} org.github.alexwibowo.spider.SpiderLauncher

