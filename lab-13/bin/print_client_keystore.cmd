@echo off

call ..\..\env.cmd

set KEYSTORE_FILE=..\src\main\resources\keystores\client_keystore.jks
set KEYSTORE_PASSWORD=client_ks_pass

%JAVA_HOME%\bin\keytool -list -keystore %KEYSTORE_FILE% -storepass %KEYSTORE_PASSWORD%
