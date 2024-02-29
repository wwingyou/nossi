@echo off

javac Solution.java

java -cp .;../../java;../../lib/jansi-2.4.0.jar nossi.Main < testcase.txt

pause
