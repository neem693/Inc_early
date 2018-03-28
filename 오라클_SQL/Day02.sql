SELECT *
FROM EMP;


SELECT *
FROM EMP
WHERE SAL>=3000;

SELECT *
FROM EMP
WHERE SAL<>3000;


SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE ENAME = 'FORD';



select empno,ename,sal
from emp
where ename = 'SCOTT';

select *
from emp;


select empno,ename,sal
from emp
where ename >= 'SCOTT';

select *
from emp
where HIREDATE >='1981/01/01';

select *
from emp
where HIREDATE = '1982/01/23';


select *
from emp
where HIREDATE = '82/01/23';


select *
from emp
where HIREDATE = '82-01-23';


select *
from emp
where HIREDATE = '1982-01-23';


select *
from emp
where deptno =10 or job ='MANAGER';


select *
from emp
where deptno =10 and JOB = 'MANAGER';

select ENAME, deptno, job
from emp
where deptno =10 and job = 'MANAGER';


select *
from emp
where deptno =10 and JOB = 'MANAGER' and HIREDATE = '1982-01-23';



select *
from emp
where deptno =10 and JOB = 'MANAGER' or HIREDATE = '1982-01-23';


select *
from emp
where not deptno = 10;







--2000에서 3000 사이의 급여를 받는 사원을 조회해 봅시다.
SELECT *
FROM EMP
WHERE SAL >=2000 AND SAL<=3000;




--커미션이 300 이거나 500 또는 1400 인 사원들을 조사해보자
select *
from emp
where COMM=300 or comm=500 or comm=1400;


--EMPNO이 7521 이거나 7654 이거나 7844 인 사원들의 사원 번호와 급여를 검색하는 쿼리문을 비교 연산자와 OR 논리 연산자 사용하여 작성하시오.

select EMPNO,sal
from emp
where empno =7521 or empno=7654 or empno=7844;

select EMPNO,sal
from emp
where empno in(7521,7654,7844);


select *
from emp
where sal BETWEEN 2000 AND 3000;

select *
from emp
where comm in(300,500,1400);

select *
from emp
where comm<>300 and comm<>500 and comm<>1400;

select empno,ename
from emp
where empno <>7521 and empno <> 7654 and empno <>7844;


select empno,ename
from emp
where empno not in(7521,7654,7844);

select empno,ename
from emp
where empno in(7521,7654,7844);

select *
from emp
where ename LIKE 'F___';

select *
from emp
where ename LIKE '%D';

select *
from emp
where ename LIKE '%O%';

select *
from emp
where ename like '_O%';

select *
from emp
where ename like 'J%';

select *
from emp
where ename like '%A%';


select *
from emp
where ename like '%N';


--사원들 이름이 K로 시작하는 사원의 사원번호와 사원이름을 출력하시오.
select * 
from emp
where ename like 'K%';

--이름 중에 K를 포함하는 사원의 사원번호와 사원이름을 출력하시오.
select * 
from emp
where ename like '%K%';

--이름이 K로 끝나는 사원의 사원번호와 사원이름을 출력하시오.
select *
from emp
where ename like '%K';

select *
from emp
where sal like '1%';

select *
from emp
where hiredate like '82%';

select *
from emp
where ename like '__A%';


select *
from emp
where ename like '__A%';


select *
from emp
where ename like '_A%' and job like 'C%';

select *
from emp
where COMM IS NULL;

select *
from  emp
where mgr is null;

select *
from emp
ORDER by sal;




--EMP 테이블의 자료를 입사일을 오름차순으로 정렬하여 최근 입사한 직원을 먼저 출력하되 사원번호, 사원명, 직급, 입사일 칼럼을 출력하는 쿼리문을 작성하시오

select EMPNO, ENAME,JOB,HIREDATE 
from emp
order by hiredate asc;
--디폴트로 오름차순
select EMPNO, ENAME,JOB,HIREDATE 
from emp
order by hiredate;

select empno,ename,job,hiredate,comm
from emp
order by comm asc;

--보안
select empno,ename,job,hiredate,comm as 수당
from emp
order by 수당 asc;

select *
from emp
order by sal asc, ename asc;


--부서 번호가 빠른 사원부터 출력하되 같은 부서내의 사원을 출력할 경우 최근에 입사한 사원부터 출력하도록 하되 사원 번호, 입사일, 사원 이름, 급여 순으로 출력하시오.
select EMPNO,HIREDATE,ENAME,SAL,DEPTNO
from emp
order by deptno asc, HIREDATE desc;



--1. 이름에 '철수'가 포함되면서 급여가 2000이상인 직원의 이름과 급여 출력(단 급여의 별명은 월급) //철수는 O로 변경
SELECT ENAME,SAL
FROM EMP
WHERE SAL>=2000 AND ENAME LIKE '%철수%';

SELECT ENAME,SAL
FROM EMP
WHERE SAL>=2000 AND ENAME LIKE '%O%';

--2. 100% 인상급여액을 함께 출력(단 인상급여액의 별명은 새월급)
SELECT ENAME,SAL,SAL*2 AS 새월급
FROM EMP
WHERE SAL>2000 AND ENAME LIKE '%철수%';

SELECT ENAME,SAL,SAL*2 AS 새월급
FROM EMP
WHERE SAL>2000 AND ENAME LIKE '%O%';


--3. 부서번호가 10또는 20이면서 수당이 NULL인 직원의 이름과 수당 출력(단 직원의 이름으로 오름정렬)
SELECT ENAME,COMM,DEPTNO
FROM EMP
WHERE DEPTNO=10 OR DEPTNO=20 AND COMM IS NULL
ORDER BY ENAME ASC;

SELECT ENAME,SAL,DEPTNO,COMM
FROM EMP
WHERE DEPTNO in (10,20) AND COMM IS NULL
ORDER BY ENAME ASC;

SELECT ENAME,COMM,DEPTNO
FROM EMP
WHERE DEPTNO IN (10,20) AND COMM IS NULL
ORDER BY DEPTNO,ENAME ASC;
