@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem
@rem https://github.com/gradle/gradle/blob/124712713a77a6813e112ae1b68f248deca6a816/subprojects/plugins/src/main/resources/org/gradle/api/internal/plugins/windowsStartScript.txt

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  %{-- Copyright 2021 The Terasology Foundation --}%
%{-- SPDX-License-Identifier: Apache-2.0 --}%

${applicationName} startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.\

set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%${appHomeRelativePath}

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and ${optsEnvironmentVar} to pass JVM options to this script.
set DEFAULT_JVM_OPTS=${defaultJvmOpts}

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

<% if ( mainClassName.startsWith('--module ') ) { %>set MODULE_PATH=$modulePath<% } %>

@rem Execute ${applicationName}
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %${optsEnvironmentVar}% <% if ( appNameSystemProperty ) { %>"-D${appNameSystemProperty}=%APP_BASE_NAME%"<% } %> <% if ( mainClassName.startsWith('--module ') ) { %>--module-path "%MODULE_PATH%" <% } %>-jar lib\\Terasology.jar %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable ${exitEnvironmentVar} if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%${exitEnvironmentVar}%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
