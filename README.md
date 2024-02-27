# 노씨데브 자바코드 실행기

## 빌드
`build` 스트립트의 실행권한 수정 후 실행하면 `nossi/` 디렉토리의 파일들이 빌드되어 `.nossi` 디렉토리에 클래스파일들이 생성된다.
```bash
chmod 755 build
./build
```

## 아카이빙
`archive` 스크립트의 실행권한 수정 후 실행하면 실행기와 테스트케이스가 포함된 zip 파일이 `dest/` 디렉토리 아래에 생성된다.
```bash
$ chmod 755 archive
$ ./archive 덧셈뺄셈
[info] 'dest' 디렉토리가 존재하지 않습니다. 새로 생성합니다.
'dest/덧셈뺄셈.zip'가 성공적으로 생성되었습니다.
```

## 실행기 디렉토리 구조
빌드후 생성된 zip 파일을 압축해제하면 다음과 같은 디렉토리 구조를 볼 수 있다.
```bash
├── .nossi                  # 실행기 클래스파일 디렉토리
├── Solution.java           # 솔루션 파일
├── run_java                # MacOS, Linux 실행기
├── run_java.bat            # Windows 실행기
└── testcase                # 테스트케이스 디렉토리
    ├── 1
    │   ├── input.txt
    │   └── output.txt
    └── 2
        ├── input.txt
        └── output.txt
```

## 사용법
zip 파일을 압축해제 후 `Solution.java` 파일에 풀이를 작성한다. 
풀이 작성 후 실행기를 실행시키면 풀이 결과가 출력된다.

### MacOS, Linux
```bash
$ ./run_java
-- test 1 --
출력
3
정답
3
-- test 2 --
출력
15
정답
15
```

### Windows
`run_java.bat`파일을 더블클릭하거나 cmd에서 실행하면 같은 결과가 출력된다.
