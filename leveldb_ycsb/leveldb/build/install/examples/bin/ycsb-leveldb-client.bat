@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  ycsb-leveldb-client startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and YCSB_LEVELDB_CLIENT_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\examples.jar;%APP_HOME%\lib\grpc-netty-1.0.1.jar;%APP_HOME%\lib\grpc-protobuf-1.0.1.jar;%APP_HOME%\lib\grpc-stub-1.0.1.jar;%APP_HOME%\lib\core-0.1.4.jar;%APP_HOME%\lib\netty-codec-http2-4.1.3.Final.jar;%APP_HOME%\lib\grpc-core-1.0.1.jar;%APP_HOME%\lib\guava-19.0.jar;%APP_HOME%\lib\protobuf-java-3.0.0.jar;%APP_HOME%\lib\protobuf-java-util-3.0.0.jar;%APP_HOME%\lib\grpc-protobuf-lite-1.0.1.jar;%APP_HOME%\lib\jackson-mapper-asl-1.9.4.jar;%APP_HOME%\lib\jackson-core-asl-1.9.4.jar;%APP_HOME%\lib\checkstyle-5.0.jar;%APP_HOME%\lib\jdom-1.1.jar;%APP_HOME%\lib\google-collections-1.0.jar;%APP_HOME%\lib\slf4j-api-1.6.4.jar;%APP_HOME%\lib\netty-codec-http-4.1.3.Final.jar;%APP_HOME%\lib\netty-handler-4.1.3.Final.jar;%APP_HOME%\lib\grpc-context-1.0.1.jar;%APP_HOME%\lib\jsr305-3.0.0.jar;%APP_HOME%\lib\gson-2.3.jar;%APP_HOME%\lib\antlr-2.7.6.jar;%APP_HOME%\lib\commons-beanutils-core-1.7.0.jar;%APP_HOME%\lib\commons-cli-1.0.jar;%APP_HOME%\lib\commons-logging-1.0.3.jar;%APP_HOME%\lib\netty-codec-4.1.3.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.3.Final.jar;%APP_HOME%\lib\netty-transport-4.1.3.Final.jar;%APP_HOME%\lib\commons-collections-2.0.jar;%APP_HOME%\lib\commons-lang-1.0.jar;%APP_HOME%\lib\netty-common-4.1.3.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.3.Final.jar;%APP_HOME%\lib\junit-3.7.jar

@rem Execute ycsb-leveldb-client
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %YCSB_LEVELDB_CLIENT_OPTS%  -classpath "%CLASSPATH%" io.grpc.ycsb_leveldb.YCSBLevelDBClient %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable YCSB_LEVELDB_CLIENT_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%YCSB_LEVELDB_CLIENT_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
