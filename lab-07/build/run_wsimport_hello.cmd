@echo off

call ..\..\env.cmd

set WSDL_LOCATION=..\src\main\resources\hello.wsdl
set OUTPUT_LOCATION=.\output\hello

:CHK_WSDL
if exist %WSDL_LOCATION% goto CHK_OUTPUT
echo WSDL not found (%WSDL_LOCATION%)
goto EDN

:CHK_OUTPUT
if exist %OUTPUT_LOCATION% goto RUN
mkdir %OUTPUT_LOCATION%

:RUN
%JAVA_HOME%\bin\wsimport.exe -keep -d %OUTPUT_LOCATION% %WSDL_LOCATION%

:EDN