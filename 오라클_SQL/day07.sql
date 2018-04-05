--SCOTT 보다 입사일이 늦은 사원과 같은 부서에서 근무하는 사원들의 부서명,이름,급여
--늦는다는 건 더 많다는 걸 의미한다.
--즉, SCOTT보다 더 많은 HIREDATE가 요구된다.
select deptno,ename,sal
from emp
where hiredate > (select hiredate
from emp
where ename = 'SCOTT');

--같은 테이블이 서브쿼리에 나오므로 내부조인을 한다.

select f.deptno,f.ename,f.sal
from emp e inner join emp f
on e.hiredate<f.hiredate
where e.ename = 'SCOTT'and
e.ename <> f.ename;

desc salgrade;

--SCOTT의 사번,부서명,급여,급여등급 출력
select d.dname,e.ename,e.sal,s.grade
from emp e,dept d,salgrade s
where e.deptno = d.deptno 
and e.sal between s.losal and s.hisal
and e.ename = 'SCOTT';  

select * 
from salgrade;

select empno,dname,sal,grade
from emp  inner join dept  
on emp.deptno = dept.deptno 
inner join salgrade 
on emp.sal between salgrade.losal and salgrade.hisal
where emp.ename = 'SCOTT';


select *
from salgrade;




