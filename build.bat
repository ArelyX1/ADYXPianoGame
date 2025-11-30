@echo off
REM Simple Windows build script (cmd.exe)
IF EXIST out rmdir /s /q out
mkdir out

setlocal enabledelayedexpansion
set "JFILES="
for /R %%f in (src\*.java) do (
  set "JFILES=!JFILES! "%%f""
)

echo Compiling Java sources...
javac -d out %JFILES%

echo Creating manifest...
echo Main-Class: dynamic_beat_17.Main>manifest.txt

echo Building JAR...
jar cfm app.jar manifest.txt -C out .

echo Adding resources...
if exist lib ( 
  for %%L in (lib\*.jar) do (
    echo Unpacking %%L into JAR...
    mkdir tmp_unpack 2>nul
    pushd tmp_unpack
    jar xf ..\%%L
    popd
    jar uf app.jar -C tmp_unpack .
    rmdir /s /q tmp_unpack
  )
)

jar uf app.jar -C src images -C src music

move /Y app.jar dist.jar
del manifest.txt

echo Built dist.jar
