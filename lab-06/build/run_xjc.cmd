@echo off

call ..\..\env.cmd

set SCHEMA_LOCATION=..\src\main\resources\library.xsd
set OUTPUT_LOCATION=..\src\main\java

%JAVA_HOME%\bin\xjc.exe -d %OUTPUT_LOCATION% %SCHEMA_LOCATION%

