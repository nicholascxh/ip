@ECHO OFF
chcp 65001 > nul

REM Create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Delete old saved tasks to start fresh
if exist ..\text-ui-test\data\Dasani.txt del ..\text-ui-test\data\Dasani.txt

REM Delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM Compile the Java files with UTF-8 encoding
javac -encoding UTF-8 -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\dasani\*.java ..\src\main\java\dasani\exception\*.java ..\src\main\java\dasani\task\*.java ..\src\main\java\dasani\task\type\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM Run the program with test input, store output in ACTUAL.TXT
java -classpath ..\bin dasani.Dasani < input.txt > ACTUAL.TXT

REM Compare ACTUAL.TXT with EXPECTED.TXT
FC ACTUAL.TXT EXPECTED.TXT
