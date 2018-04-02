DESC EMP;

SELECT *
FROM EMP;

select *
from dept;

SELECT DEPTNO, MAX(SAL),MEDIAN(SAL),MIN(SAL),COUNT(*)
FROM EMP
GROUP BY DEPTNO;


SELECT COMM,NVL2(COMM,0,1)
FROM EMP;
------------------------조인----------------------
SELECT *
FROM EMP,DEPT;

desc emp;

SELECT *
FROM EMP,DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO;

SELECT E.ENAME,D.DNAME
FROM DEPT D,EMP E
WHERE E.ENAME = 'SCOTT' AND D.DEPTNO = E.DEPTNO;

select * from emp;
select * from dept;

SELECT D.LOC, E.ENAME, E.SAL
FROM DEPT D ,EMP E
WHERE D.LOC = 'NEW YORK' AND D.DEPTNO = E.DEPTNO;

--ACCOUNTING 부서 소속 사원의 이름과 입사일을 출력하시오.

SELECT D.DNAME,E.ENAME,E.HIREDATE
FROM DEPT D, EMP E
WHERE D.DNAME = 'ACCOUNTING' AND D.DEPTNO = E.DEPTNO;

--직급이 MANAGER인 사원의 이름, 부서명을 출력하시오

SELECT E.JOB,E.ENAME,D.DNAME
FROM DEPT D, EMP E
WHERE E.JOB = 'MANAGER' AND E.DEPTNO = D.DEPTNO;



select *
from salgrade;


desc salgrade;


SELECT E.ENAME,E.SAL,S.GRADE
FROM EMP E , SALGRADE S
WHERE E.SAL BETWEEN S.LOSAL AND S.HISAL;

select e.ename, d.dname, e.deptno
from emp e, dept d
where e.deptno<>d.deptno;


select * from dept;

select* from emp;

select e.ename,d.dname,e.deptno
from emp e, dept d
where e.deptno (+)= d.deptno;


select e.ename,d.dname,e.deptno
from emp e, dept d
where e.deptno = d.deptno(+);

select *
from emp
union all
select *
from emp;

select e.ename,d.dname,e.deptno
from emp e, dept d
where e.deptno (+)= d.deptno

union

select e.ename,d.dname,e.deptno
from emp e, dept d
where e.deptno = d.deptno(+);

select e.ename ||'의 메이저는 '|| m.ename || '이다.'
from emp e, emp m
where e.mgr = m.empno and e.ename = 'SMITH';


SELECT E.ENAME AS 사원, M.ENAME AS 메니저
FROM EMP E, EMP M
WHERE E.MGR = M.EMPNO AND E.ENAME = 'SMITH';


SELECT E.ENAME EMPLOYEE, F.ENAME SAME_LOCATION
FROM EMP E, EMP F
WHERE (E.ENAME = 'SCOTT') AND 
(E.DEPTNO = F.DEPTNO) and
(e.ename <> f.ename);


SELECT ENAME,DNAME
FROM EMP INNER JOIN DEPT
ON EMP.DEPTNO=DEPT.DEPTNO
WHERE ENAME = 'SCOTT';

/*이거 안된다.*/


SELECT E.ENAME, E2.ENAME
FROM EMP E INNER JOIN EMP E2
ON E.DEPTNO = E2.DEPTNO
WHERE E.ENAME = 'SCOTT'
AND E.ENAME <> E2.ENAME;

SELECT E.ENAME EMPLOYEE, F.ENAME SAME_LOCATION
FROM EMP E INNER JOIN EMP F
on E.DEPTNO = F.DEPTNO
WHERE (E.ENAME = 'SCOTT') AND 
(e.ename <> f.ename);


select ename, dname
from emp inner join dept
using(deptno);


--사원자신사번, 입사일, 자신보다 입사일 빠른 사원수 출력(셀프조인)


SELECT E.ENAME, E.HIREDATE,COUNT(F.HIREDATE)
FROM EMP E,EMP F
WHERE E.HIREDATE>F.HIREDATE(+) 
GROUP BY E.ENAME,E.HIREDATE
ORDER BY E.HIREDATE;


select e.ename,e.hiredate,count(f.hiredate)
from emp e RIGHT  JOIN emp f
on e.hiredate>f.hiredate
group by e.ename, e.hiredate
order by e.hiredate;



SELECT E.ENAME, E.HIREDATE,COUNT(F.HIREDATE)
FROM EMP E,EMP F
WHERE E.HIREDATE=F.HIREDATE and E.ENAME <> F.ENAME
GROUP BY E.ENAME,E.HIREDATE
ORDER BY E.HIREDATE;



select e.ename,e.hiredate,count(f.hiredate)
from emp e INNER JOIN emp f
on e.hiredate>f.hiredate
group by e.ename,e.hiredate
where e.hiredate RIGHT JOIN f.hiredate




select e.ename,e.hiredate,f.hiredate
from emp e , emp f
where e.hiredate>f.hiredate
order by e.hiredate;

select * from emp;

select hiredate,job,count(ename)
from emp
group by cube(hiredate,job);


select hiredate,job,count(ename)
from emp
group by grouping sets(hiredate,job);
