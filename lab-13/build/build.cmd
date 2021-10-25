@echo off

call ..\..\env.cmd

%ANT_HOME%\bin\ant.bat -f build.xml %*
