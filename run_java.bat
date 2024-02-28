@echo off

rem java 설치여부 확인
where java > nul
if errorlevel 1 (
	echo 오류: "java"가 설치되어 있지 않습니다.
	goto :EOF
)
rem javac 설치여부 확인
where javac > nul
if errorlevel 1 (
	echo 오류: "javac"가 설치되어 있지 않습니다.
	goto :EOF
)

rem java 파일 컴파일
javac Solution.java

if errorlevel 1 (
	echo 오류: 컴파일 에러
	goto :EOF
)

java -cp .nossi\jansi-2.4.0.jar;.nossi nossi.Main < testcase.txt

pause