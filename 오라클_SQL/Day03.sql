SELECT ABS(-1)
FROM DUAL;

SELECT -10, ABS(-10)
FROM DUAL;

SELECT 34.5756, FLOOR(34.5756)
FROM DUAL;

SELECT 34.5756, FLOOR(34.5756) || '원'
FROM DUAL;

SELECT 34.5756, FLOOR(:DATA) || '원'
FROM DUAL;

SELECT 34.5756, ROUND(:DATA)
FROM DUAL;

SELECT 34.5756,ROUND(:DATA,:POS)
FROM DUAL;

SELECT 34.5756,CEIL(:DATA)
FROM DUAL;





SELECT 34.5756,TRUNC(:DATA,:POS)
FROM DUAL;



SELECT 235 AS N1,3 AS N2,MOD(235,3) AS 나머지,TRUNC(235/3) AS 몫
FROM DUAL;


SELECT *
FROM EMP
WHERE MOD(EMPNO,2) = 0;


SELECT *
FROM EMP
WHERE MOD(EMPNO,2) = 1;

SELECT 'Welcome to Oracle', UPPER('Welcome to Oracle') AS 대문자로
FROM DUAL;

SELECT 'Welcome to Oracle', LOWER('Welcome to Oracle') AS 대문자로
FROM DUAL;

SELECT 'welcome to oRacle', INITCAP('welcome to oRacle') AS 대문자로
FROM DUAL;

SELECT EMPNO, ENAME, JOB
FROM EMP
WHERE JOB = UPPER('manager');

SELECT LENGTH('oracle'),LENGTH('오라클')
FROM DUAL;

SELECT LENGTHB('oracle'),LENGTHB('오라클')
FROM DUAL;

SELECT SUBSTR('Welcome to Oracle',2)
FROM DUAL;


SELECT SUBSTR('Welcome to Oracle',2,LENGTH('Welcome to Oracle'))
FROM DUAL;

SELECT SUBSTR('welcome to oracle',-6)
FROM DUAL;

SELECT ENAME, SUBSTR(HIREDATE,1,2)||'년' AS 년,SUBSTR(HIREDATE,4,2)||'월' AS 월,SUBSTR(HIREDATE,-2,2)||'일' AS 일 
FROM EMP;


SELECT *
FROM EMP
WHERE SUBSTR(HIREDATE,4,2) = '09';



SELECT *
FROM EMP
WHERE SUBSTR(HIREDATE,1,2) = '87';

--다음은 이름이 E로 끝나는 사원을 검색해 보도록 합시다. SUBSTR 함수를 이용하여 ENAME 컬럼의 마지막 문자 한개만 추출해서 이름이 E로 끝나는 사원을 검색해 보도록 하시오.

SELECT *
FROM EMP
WHERE SUBSTR(ENAME,-1,1) = 'E';

--사원의 뒤에서 두 번째자리가 E로 끝나는 사원을 검색해보자.

SELECT *
FROM EMP
WHERE SUBSTR(ENAME,-2,1) = 'E';

SELECT LTRIM(EXTRACT(YEAR FROM HIREDATE), '19') as 년도 
FROM EMP;


SELECT *
FROM EMP
WHERE EXTRACT(YEAR FROM HIREDATE) = '1981';





SELECT INSTR('Welcome to Oracle','o',1,2)
FROM DUAL;

SELECT INSTR('welcome to oracle','e',6,2)
FROM DUAL;

SELECT INSTR('welcome to oracle','e',6)
FROM DUAL;

select substr('tester@naver.com',1,instr('tester@naver.com','@')-1) as ID
from dual;

select substr('tester@naver.com',instr('tester@naver.com','@')+1,instr('tester@naver.com','.')-instr('tester@naver.com','@')-1)
from dual;


select substr(:email,instr(:email,'@')+1,instr(:email,'.')-instr(:email,'@')-1)
from dual;


--이름의 세 번째 자리가 R인 사원을 검색하기
select *
from emp
where ename like '__R%';

select *
from emp
where substr(ename,3,1)='R';

select *
from emp
where instr(ename,'R') = 3;

select 
LPAD('oracle',20,'#')
from dual;



select 
TRIM('a' from 'aaaaORAcleaaaa')
from dual;


select 
Ltrim(substr(hiredate,4,2)||'월','0')
from emp;




select sysdate,SYSTIMESTAMP
from dual;



select sysdate -1,sysdate,sysdate + 3
from dual;

select  floor(sysdate - hiredate) as 근무일수
from emp;


select sysdate, to_char(sysdate,'YYYY-MM-DD')
from dual;

select sysdate,to_char(sysdate,'YYYY-MM-DD DAY')
from dual;

select to_char(hiredate,'YYYY-MM-DD DAY')
from emp;


select to_char(hiredate,'mm')
from emp;


select to_char(sysdate,'yyyy-mm-dd day am hh:mi')
from dual;


select to_char(1230000)
from dual;

select *
from emp;

select 
nvl(comm,0)+100 , nvl(mgr,'X')
from emp;

desc emp;

select
nvl(comm,0)+100 , nvl(to_char(mgr),'X')
from emp;


select ename, hiredate 
from emp
where hiredate = to_date(19810220,'yyyymmdd');

select ename,hiredate 
from emp
where hiredate = to_date('1981/02/20','yyyy/mm/dd');