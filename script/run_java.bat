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
javac Solution.java

if errorlevel 1 (
	echo ����: ������ ����
	goto :EOF
)

java -cp .nossi\jansi-2.4.0.jar;.nossi nossi.Main < testcase.txt

pause