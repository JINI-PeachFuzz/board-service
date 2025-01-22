1. 관리자  - /admin
1) 게시판 설정

- POST /admin/config : 게시판 설정 등록, 수정 처리

2) 게시판 목록
- GET /admin/config: 게시판 설정 목록 + 게시판 설정을 조회하기 위한 커맨드 객체

3) 게시판 한개 또는 여러개 일괄 수정
- PATCH /admin/config : 일괄 수정

4) 게시판 한개 또는 여러개 삭제
- DELETE /admin/config

2. 사용자 - /
1) 게시글
- GET /config/{bid} : 게시판 설정 한개 조회
- POST /save : 게시글 등록, 수정 처리
- GET /view/{seq} : 게시글 한개 조회  - 글보기, 글수정
- GET /list/{bid} : 게시글 목록 조회
- DELETE /{seq} : 게시글 한개 삭제

2) 댓글 - /comment
- POST /comment/save : 댓글 작성, 수정
- GET /comment/view/{seq} : 댓글 한개 조회 : 댓글 수정시 사용된다.
- GET /comment/list/{seq} : 게시글별 댓글 목록, seq : 게시글 번호
- DELETE /comment/{seq} : 댓글 한개 삭제


3. 커맨드 객체 구성

4. 커맨드 객체 기본 검증 및 추가 Validator 구성
