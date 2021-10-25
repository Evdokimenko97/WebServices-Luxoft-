@echo off

call ..\..\env.cmd

set WSDL_LOCATION=http://localhost:8080/ws/hello?wsdl
set OUTPUT_LOCATION=..\src\main\java

%JAVA_HOME%\bin\wsimport.exe -keep -b bindings.xml -d %OUTPUT_LOCATION% %WSDL_LOCATION%

