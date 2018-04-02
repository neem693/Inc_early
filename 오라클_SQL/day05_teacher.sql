 --Join: 테이블 결합(열 결합 및 행 결합)
--1. 내부(inner)조인 
--Cross Join
SELECT
    *
FROM EMP,dept;
-- Equi Join(동치,등가,항등) :공통컬럼의 값이 일치하는 행 결합
SELECT *
FROM EMP, DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO;
-- 별명지정하면 별명사용
SELECT e.ENAME, d.DNAME,e.DEPTNO
FROM EMP e, DEPT d
WHERE e.DEPTNO=d.DEPTNO
             AND e.ENAME='SCOTT';
             
  desc SALGRADE;
  SELECT
      *
  FROM SALGRADE;
 --Non-Equi Join (Equi Join비해서 거의 사용X)
  SELECT ENAME, SAL, GRADE
FROM EMP, SALGRADE 
WHERE SAL BETWEEN LOSAL AND HISAL;
  
 SELECT e.ENAME, d.DNAME,e.DEPTNO
FROM EMP e, DEPT d
WHERE e.DEPTNO<>d.DEPTNO; 
-----------------------------
--2.외부조인
--왼쪽 외부조인
--사원이 없는(null) 부서(40번 OPERATIONS)를 조인 출력
--null인 테이블 조인컬럼에 (+) 
 SELECT e.ENAME, d.DNAME,e.DEPTNO
FROM EMP e, DEPT d
WHERE e.DEPTNO(+)=d.DEPTNO;
--그밖에 오른쪽 외부조인,양쪽 외부조인
-------------------
--UNION 합집합(행병합) : 중복행들(교집합)은 제거
--UNION ALL 합집합(행병합) : 중복행들(교집합)은 포함
SELECT
    *
FROM EMP
UNION ALL
SELECT
    *
FROM EMP;

 SELECT e.ENAME, d.DNAME,e.DEPTNO
FROM EMP e, DEPT d
WHERE e.DEPTNO(+)=d.DEPTNO

UNION 
             
SELECT e.ENAME, d.DNAME,e.DEPTNO
FROM EMP e, DEPT d
WHERE e.DEPTNO=d.DEPTNO(+);

------------------
--3. 셀프조인 : 자신을 다른 별명을 주고 조인 
--공통컬럼명은 달라도 됨
select employee.ename|| '의 매니저는'|| manager.ename||'입니다' 	 
from emp employee, emp manager
	     where employee.mgr = manager.empno and EMPLOYEE.ENAME='SMITH';


SELECT DEPTNO
FROM emp
WHERE ENAME='SCOTT';
------------------------------
SELECT ENAME
FROM emp
WHERE DEPTNO=20;
--e2.ENAME 같은 부서사원명
SELECT e.ENAME,e2.ENAME
FROM emp e,emp e2
WHERE e.DEPTNO=e2.DEPTNO
and e.ENAME='SCOTT'
and E.ENAME <> E2.ENAME;
----------------------------------
-- 부서별 대그룹의 직급별 소그룹으로 분류 집계(매개변수 순서 중요) : 계층분석
SELECT DEPTNO,job, SUM(sal)
     FROM emp
     GROUP BY  DEPTNO,job;
     
-- 부서별 대그룹의 직급별 소그룹으로 분류 집계(매개변수 순서 중요) : 계층분석 및 소계,총합계 
SELECT DEPTNO,job, SUM(sal)
     FROM emp
     GROUP BY ROLLUP( DEPTNO,job);
-- ROLLUP + 직급별 소그룹으로 분류 소계 : 다차분석 
     SELECT DEPTNO,job, SUM(sal)
     FROM emp
     GROUP BY CUBE(DEPTNO,job);
-- 컬럼개별 단순 집계     
     SELECT DEPTNO,job, SUM(sal)
     FROM emp
     GROUP BY GROUPING SETS (DEPTNO,job);
             