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
javac *.java

if errorlevel 1 (
	echo 오류: 컴파일 에러
	goto :EOF
)

rem index가 for문 안에서 증가되도록 하기 위한 delayed expansion 세팅
setlocal ENABLEDELAYEDEXPANSION
set /a index=0
for /d %%D in ("%cd%\testcase\*") do (
	set /a index+=1
	echo -- test !index! --
	echo 출력
	java Main < "%%D\input.txt"
	echo 정답
	type "%%D\output.txt"
)
endlocal


pause