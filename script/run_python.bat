@echo off

rem python ��ġ���� Ȯ��
where python > nul
if errorlevel 1 (
	echo ����: "java"�� ��ġ�Ǿ� ���� �ʽ��ϴ�.
	goto :EOF
)

rem index�� for�� �ȿ��� �����ǵ��� �ϱ� ���� delayed expansion ����
setlocal ENABLEDELAYEDEXPANSION
set /a index=0
for /d %%D in ("%cd%\testcase\*") do (
	set /a index+=1
	echo -- test !index! --
	echo ���
	python main.py < "%%D\input.txt"
	echo ����
	type "%%D\output.txt"
)
endlocal


pause