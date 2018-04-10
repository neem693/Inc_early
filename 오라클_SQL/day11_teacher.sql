CREATE INDEX IDX_EMP_ENAME
ON EMP(ENAME);

SELECT  EMPNO, ENAME 
FROM EMP 
--WHERE ENAME='FORD';
WHERE SUBSTR(ENAME,1,1)='F';
--uk,pk컬럼는 인덱스가 자동으로 생성
--원본테이블과 별개로 인덱스 테이블(인덱스컬럼 ENAME의 행값과 행주소(ROWID) 저장) 생성
--행값은 정렬되어 저장
--DQL 속도 빠름(INDEX SCAN), DML 처리속도 느려짐(행들의 재정렬)
--INDEX SCAN을 못할경우 FULL SCAN
SELECT 
   ROWID,ENAME
FROM EMP
ORDER BY ENAME ;

CREATE INDEX IDX_EMP01_JOB
ON EMP01(JOB);

--------------------------------------------------
CREATE INDEX IDX_EMP01_HIREDATE
ON EMP01(HIREDATE);
/***********************************************************
WHERE 절에 자주 사용되는 컬럼이거나
조회결과가 전체 데에터 개수의 3-5% 미만인경우 
INDEX SCAN이 효율적임
HIREDATE는 '20180410'과 같다
where HIREDATE='20180410'
--FULL SCAN (인덱스컬럼이 가공연산이 되면 인덱스 비참조)
where to_char(HIREDATE,'yyyymmdd')='20180410'
->
--INDEX SCAN
where HIREDATE=to_date('20180410','yyyymmdd')

where substr(today,1,3)='화요일'
->
where today like'화요일%'

※ 나무(TREE) 구조
계층구조(길찾기 (다음층,다음층... ))

***********************************************************/
-- 전역적 DB (테이블스페이스들의 집합)
-- 테이블스페이스에 DB객체들이 저장 관리
-- XE DB에 테이블스페이스(파일 = DBF) 존재

-- system 계정
create tablespace myts
datafile 'C:\oraclexe\app\oracle\oradata\XE\myts.dbf' size 10m
autoextend on next 50m maxsize 200m; 

create user user01 identified by "oracle" 
default tablespace myts 
quota UNLIMITED on myts;
--quota 10M on myts;

grant create session
to user01;



create table T01(
C number(6)
);
