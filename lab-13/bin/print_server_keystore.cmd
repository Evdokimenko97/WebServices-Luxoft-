@echo off

call ..\..\env.cmd

set KEYSTORE_FILE=..\src\main\resources\keystores\server_keystore.jks
set KEYSTORE_PASSWORD=server_ks_pass

%JAVA_HOME%\bin\keytool -list -keystore %KEYSTORE_FILE% -storepass %KEYSTORE_PASSWORD%
