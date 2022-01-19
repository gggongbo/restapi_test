# 공지사항 관리 REST API ver1.0
---

## 개요
### * 공지사항 등록, 수정, 삭제, 조회 API
### * 주요 기능
* 공지사항 단건 조회/전체 조회
* 공지사항 등록 및 첨부파일 등록
* 공지사항 수정 및 첨부파일 등록
* 공지사항 삭제 및 첨부파일 삭제(데이터 delete)

## 개발환경 및 실행방법
### * 개발환경
* Framework : Spring Boot 2.6.2
* Database : MariaDB 10.6.5
* Program Language : Java(OpenJDK 1.8.0)
* Build Tool : Gradle 7.3.2
* IDE : Eclipse Photon

### * 실행방법
1. master 브랜치 소스 clone
2. 첫 실행 시에 application.properties의 spring.jpa.hibernate.ddl-auto=create로 변경
(첫 실행 완료 후 create -> validate로 변경)
3. application.properties의 file.upload.path 변경하여 파일 업로드 경로 설정
3. IDE에서 Spring Boot App 실행

## API 정보
No|Method|URI|Description
---|---|---|---|
1|GET|/board/notice/get|공지사항 단건 조회
2|GET|/board/notice/getList|공지사항 전체 조회
3|POST|/board/notice/insert|공지사항 등록(첨부파일이 등록된 경우 파일 등록 진행)
4|PUT|/board/notice/update|공지사항 수정(첨부파일이 등록된 경우 파일 등록 진행)
5|DELETE|/board/notice/delete|공지사항 삭제
6|POST|/board/file/upload|파일 업로드
7|GET|/board/file/download|파일 다운로드


## 이슈
Day|Issue
---|---|
22/1/1|Controller 로직 리턴 타입 리팩토링, 파일 다운로드 API 호출 후 응답 시 파일 MIME 타입 확인 되지 않아 발생한 이슈 해결 -> ResponseEntity에 ContentType 설정하여 해결
22/1/18|공지사항 등록/수정과 파일 업로드 데이터를 한 요청에서 동시에 받아올 수 있도록 개선
22/1/19|단위/통합테스트 코드 작성시 MockMultiPartFile 적용 및 테스트
TO-BE|파일 전체다운로드 기능구현(진행예정)
TO-BE|SpringSecurity를 활용한 세션 관리기능 구현(진행예정)
