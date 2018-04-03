select e.ename, d.dname, e.deptno
from emp e, dept d
where e.deptno(+)=d.deptno;

select e.ename, d.dname, e.deptno
from emp e left outer join dept d
on e.deptno=d.deptno;

select e.ename, d.dname, e.deptno
from emp e right outer join dept d
on e.deptno=d.deptno;



SELECT
    dname
FROM
    dept
WHERE
    deptno = (
        SELECT
            deptno
        FROM
            emp
        WHERE
            ename = 'SCOTT'
    );
    
    
  --
    select dname
    from dept
    where deptno =   (  select deptno
    from emp
    where ename = 'SCOTT');
    
    
    --SCOTT과 같은 부서에서 근무하는 사원의 이름과 부서 번호를 출력하는 SQL 문을 작성해 보시오. 
    select ename,deptno
    from emp
    where deptno = (select deptno
    from emp
    where ename = 'SCOTT');
    
    select *
    from emp;
    select *
    from emp
    where ename = 'SCOTT';
  e  c
  select e.ename,e.deptno
  from emp e INNER JOIN emp f
  on e.deptno = f.deptno
  where f.ename  = 'SCOTT'and
  f.ename <>e.ename;
  
  select e.ename,e.deptno
  from emp e, emp f
  where e.deptno = f.deptno and f.ename = 'SCOTT'and f.ename <> e.ename;
  
  
  --SCOTT와 동일한 직급을 가진 사원을 출력하는 SQL 문을 작성해 보시오.
  
  
  select *
  from emp
  where ename <>'SCOTT' and job=(select job
  from emp
  where ename = 'SCOTT');
  
  
  select e.*
  from emp e INNER JOIN emp f
  on e.job = f.job
  where f.ename = 'SCOTT' and
  e.ename <> f.ename;
  
  --SCOTT의 급여와 동일하거나 더 많이 받는 사원 명과 급여를 출력하시오.

select ename, sal
from emp
where ename <>'SCOTT' and sal >=(select sal
from emp
where ename = 'SCOTT');
  
  select *
  from dept;
  
  
--DALLAS에서 근무하는 사원의 이름, 부서 번호를 출력하시오.

select ename, deptno
from emp
where deptno in (select deptno
from dept
where loc = 'DALLAS');
  
  
  --SALES(영업부) 부서에서 근무하는 모든 사원의 이름과 급여를 출력하시오.

select ename, sal
from emp
where deptno = (select deptno
from dept
where dname = :DNAME);
  
  
  
select e.ename,e.sal
from emp e inner join dept f
on e.deptno = f.deptno
where f.dname = 'SALES';

select *
from emp;

--직속상관이 KING인 사원의 이름과 급여를 출력하시오.

select ename, sal
from emp
where mgr = (select empno
from emp
where ename = 'KING');


select e.ename,e.sal
from emp e INNER JOIN emp f
on e.mgr = f.empno
where f.ename = 'KING';


--사원테이블에서 최대급여금액와 최대급여를 받는 사원명을 출력하시오.
select ename, sal
from emp
where sal = (select max(sal) from emp);


--평균 급여를 구하는 쿼리문을 서브 쿼리로 사용하여 평균 급여보다 더 많은 급여를 받는 사원을 검색

select ename, sal
from emp
where sal> (select avg(sal) from emp);

--조인으로? 

--문제가 3000 이상 받는 사원이 소속된 부서(10번, 20번)와 동일한부서에서 근무하는 사원
select ename,sal,deptno
from emp
where deptno in (select distinct deptno
from emp
where sal>=3000);


--내부 서브 쿼리의 조인컬럼으로 이동
--where뒤의 deptno는 내부서브쿼리의 조인컬럼으로 이동

select ename, sal, deptno
from emp e
where exists (select distinct deptno
                from emp
                where e.deptno = deptno and sal>=3000);

--부서별로 가장 급여를 많이 받는 사원의 정보(사원 번호, 사원이름, 급여, 부서번호)를 출력하시오.(IN 연산자 이용)

select empno,ename,sal,deptno
from emp
where sal in(select max(sal)
from emp
group by deptno);


select empno,ename,sal,deptno
from emp e
where exists (select max(sal)
from emp
where ename = e.ename
group by deptno);



select empno,ename,sal,deptno
from emp
where exists (select distinct deptno
from emp
where sal>=3000);


select ename, sal, deptno
from emp e
where exists (select distinct deptno
                from emp
                where deptno = e.deptno and sal>=3000);



select ename, sal, deptno
from emp e
where exists (select distinct deptno
from emp
where deptno =30 and sal>=3000);

select *
from dept;

--직급(JOB)이 MANAGER인 사람의 속한 부서의 부서 번호와 부서명과 지역을 출력하시오.
select deptno,dname,loc
from dept
where deptno in(select deptno
from emp
where job = 'MANAGER')
order by deptno;


select deptno,dname,loc
from dept d
where exists (select deptno
from emp
where d.deptno = deptno and job = 'MANAGER')
order by deptno;


select ename,sal
from emp
where sal> all(select sal
from emp
where deptno =30);

select ename,sal
from emp
where sal> any(select sal
from emp
where deptno =30);


select deptno,dname,loc
from dept
where deptno in(select deptno
from emp
where job = 'MANAGER')
order by deptno;


select *
from emp;




--영업 사원들 보다 급여를 많이 받는 사원들의 이름과 급여와 직급(담당 업무)를 출력하되 영업 사원은 출력하지 않습니다.

select ename, sal,job
from emp
where sal >ALL(select sal
            from emp
            where job = 'SALESMAN');
            
            
            
            

select ename,sal
from emp
where job <> 'SALESMAN'and sal>any(select sal
from emp
where job = 'SALESMAN');







