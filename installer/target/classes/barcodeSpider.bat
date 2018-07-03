@echo off

set BARCODE_SPIDER_HOME=$INSTALL_PATH
set JAVA_HOME=$JDKPath

set PATH=%JAVA_HOME%/bin;%PATH%
set CLASSPATH=".;%BARCODE_SPIDER_HOME%/lib/*"


java -cp %CLASSPATH% org.github.alexwibowo.spider.SpiderLauncher

