# 💻 Emp-Search-System 🐣

### 🔍 JDBC를 활용한 직원검색 시스템 텀프로젝트입니다.

## [ 1️⃣ 문제 제기 ]
- COMPANY DB에서 조건에 맞는 직원들을 검색, 추가, 삭제, 수정할 수 있는 자바 프로그램을 구현한다.
- JDBC를 사용하여 MYSQL에 접근한 후, COMPANY DB를 사용한다. (COMPANY DB는 이미 존재한다고 가정하고 프로젝트를 진행한다.)
- 다음의 요청사항이 수행가능하다.
  1. EMPLOYEE 테이블의 attributes들을 모두 출력하기
  2. 검색 범위, 검색 항목 필터를 적용하여 해당되는 직원의 정보를 검색 및 출력하기
  3. 검색된 직원을 선택하고 DB에서 삭제하기
  4. 새로운 직원 정보를 GUI에서 직접 추가하기
  5. 부서별 직원 모두 출력하기
  6. 부서별 월급을 일괄 수정하기

## [ 2️⃣ 실행환경 ]
개발 환경 : 자바 버전 openjdk 18.0.2 및 intellij / eclipse
1.	Mysql 서버를 실행시킨다.
2.	/src/JDBCproject/companyDB.java 에서 아래 변수들을 프로그램을 테스트할 환경의 mysql 서버 user name, password, database name으로 변경해준다.
<img width="452" alt="image" src="https://user-images.githubusercontent.com/91872300/209252966-d59545ae-4264-4eed-bef5-d53ce379cb22.png">
3.	이후 companyDB Class의 main 함수를 실행시킨다.
4.	GUI 창에서 검색 버튼을 눌렀을 때, 콘솔에 “연결 성공” 메시지가 뜨면 성공적으로 실행된 것이다.

## [ 3️⃣ 구현 기능 요약 ]
<img width="597" alt="image" src="https://user-images.githubusercontent.com/91872300/209253049-4c627ac4-4f7b-4526-89d1-342e8e46861c.png">
<img width="598" alt="image" src="https://user-images.githubusercontent.com/91872300/209253067-9d473e5f-58c4-4f29-be85-2803c9154b84.png">
<img width="600" alt="image" src="https://user-images.githubusercontent.com/91872300/209253096-cf98aba4-668a-45ac-bdbf-ccfee1ff4ed4.png">
<img width="615" alt="image" src="https://user-images.githubusercontent.com/91872300/209253124-cb9edfb8-f9cb-4cf8-9ae6-8f2d0c712e62.png">
<img width="570" alt="image" src="https://user-images.githubusercontent.com/91872300/209253136-00173ed7-f9ac-4beb-9889-7626dae7874a.png">
<img width="599" alt="image" src="https://user-images.githubusercontent.com/91872300/209253193-e3ec54a8-82d3-4d0e-be89-2ddb0db1e67b.png">
<img width="611" alt="image" src="https://user-images.githubusercontent.com/91872300/209253219-9b105e07-1df5-40f1-a3e9-d3331bdf9f8b.png">
