@echo off

rem java ��ġ���� Ȯ��
where java > nul
if errorlevel 1 (
	echo ����: "java"�� ��ġ�Ǿ� ���� �ʽ��ϴ�.
	goto :EOF
)
rem javac ��ġ���� Ȯ��
where javac > nul
if errorlevel 1 (
	echo ����: "javac"�� ��ġ�Ǿ� ���� �ʽ��ϴ�.
	goto :EOF
)

rem java ���� ������
javac *.java

if errorlevel 1 (
	echo ����: ������ ����
	goto :EOF
)

rem index�� for�� �ȿ��� �����ǵ��� �ϱ� ���� delayed expansion ����
setlocal ENABLEDELAYEDEXPANSION
set /a index=0
for /d %%D in ("%cd%\testcase\*") do (
	set /a index+=1
	echo -- test !index! --
	echo ���
	java Main < "%%D\input.txt"
	echo ����
	type "%%D\output.txt"
)
endlocal


pause