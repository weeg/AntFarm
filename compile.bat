@echo off
IF NOT exist bin (mkdir bin)
set /p java_home="Enter the path of the java JDK: "
set JAVA_HOME=%java_home%
set java_path=%java_home%/bin
set PATH=%PATH%;%java_path%
javac -d bin src/modell/*.java src/view/*.java src/design/*.java