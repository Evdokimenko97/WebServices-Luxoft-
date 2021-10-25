@echo off

call ..\..\env.cmd


rem --- Configuration ---
set SERVER_KEYSTORE_FILE=..\src\main\resources\keystores\server_keystore.jks
set SERVER_KEYSTORE_PASSWORD=server_ks_pass
set SERVER_KEY_ALIAS=server_key
set SERVER_KEY_PASSWORD=server_key_pass

set CLIENT_KEYSTORE_FILE=..\src\main\resources\keystores\client_keystore.jks
set CLIENT_KEYSTORE_PASSWORD=client_ks_pass
set CLIENT_KEY_ALIAS=client_key
set CLIENT_KEY_PASSWORD=client_key_pass



rem --- Remove existing keystores first ---
del /F %SERVER_KEYSTORE_FILE%
del /F %CLIENT_KEYSTORE_FILE%

rem --- Generate keys for server and client. Keys will be saved to keystore files. ---
%JAVA_HOME%\bin\keytool -genkey -keyalg RSA -sigalg SHA1withRSA -validity 730 -dname "cn=localhost" ^
	-alias %SERVER_KEY_ALIAS% ^
	-keypass %SERVER_KEY_PASSWORD% ^
	-keystore %SERVER_KEYSTORE_FILE% ^
	-storepass %SERVER_KEYSTORE_PASSWORD%

%JAVA_HOME%\bin\keytool -genkey -keyalg RSA -sigalg SHA1withRSA -validity 730 -dname "cn=client" ^
	-alias %CLIENT_KEY_ALIAS% ^
	-keypass %CLIENT_KEY_PASSWORD% ^
	-keystore %CLIENT_KEYSTORE_FILE% ^
	-storepass %CLIENT_KEYSTORE_PASSWORD%

rem --- Temporary file that will be used to keep x509 certificate between export/import operations. ---
set CERT_FILE=x509.cer

rem --- Export client key from client's keystore and import it into server's keystore ---
%JAVA_HOME%\bin\keytool -export -rfc -keystore %CLIENT_KEYSTORE_FILE% -storepass %CLIENT_KEYSTORE_PASSWORD% -alias %CLIENT_KEY_ALIAS% -file %CERT_FILE%
%JAVA_HOME%\bin\keytool -import -trustcacerts -noprompt -keystore %SERVER_KEYSTORE_FILE% -storepass %SERVER_KEYSTORE_PASSWORD% -alias %CLIENT_KEY_ALIAS% -file %CERT_FILE% -noprompt

rem --- Export server key from server's keystore and import it into client's keystore ---
%JAVA_HOME%\bin\keytool -export -rfc -keystore %SERVER_KEYSTORE_FILE% -storepass %SERVER_KEYSTORE_PASSWORD% -alias %SERVER_KEY_ALIAS% -file %CERT_FILE%
%JAVA_HOME%\bin\keytool -import -trustcacerts -noprompt -keystore %CLIENT_KEYSTORE_FILE% -storepass %CLIENT_KEYSTORE_PASSWORD% -alias %SERVER_KEY_ALIAS% -file %CERT_FILE% -noprompt

rem --- Remove temporary file ---
del /F %CERT_FILE%
