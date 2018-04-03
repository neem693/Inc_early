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
    
    
    --SCOTT�� ���� �μ����� �ٹ��ϴ� ����� �̸��� �μ� ��ȣ�� ����ϴ� SQL ���� �ۼ��� ���ÿ�. 
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
  
  
  --SCOTT�� ������ ������ ���� ����� ����ϴ� SQL ���� �ۼ��� ���ÿ�.
  
  
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
  
  --SCOTT�� �޿��� �����ϰų� �� ���� �޴� ��� ��� �޿��� ����Ͻÿ�.

select ename, sal
from emp
where ename <>'SCOTT' and sal >=(select sal
from emp
where ename = 'SCOTT');
  
  select *
  from dept;
  
  
--DALLAS���� �ٹ��ϴ� ����� �̸�, �μ� ��ȣ�� ����Ͻÿ�.

select ename, deptno
from emp
where deptno in (select deptno
from dept
where loc = 'DALLAS');
  
  
  --SALES(������) �μ����� �ٹ��ϴ� ��� ����� �̸��� �޿��� ����Ͻÿ�.

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

--���ӻ���� KING�� ����� �̸��� �޿��� ����Ͻÿ�.

select ename, sal
from emp
where mgr = (select empno
from emp
where ename = 'KING');


select e.ename,e.sal
from emp e INNER JOIN emp f
on e.mgr = f.empno
where f.ename = 'KING';


--������̺��� �ִ�޿��ݾ׿� �ִ�޿��� �޴� ������� ����Ͻÿ�.
select ename, sal
from emp
where sal = (select max(sal) from emp);


--��� �޿��� ���ϴ� �������� ���� ������ ����Ͽ� ��� �޿����� �� ���� �޿��� �޴� ����� �˻�

select ename, sal
from emp
where sal> (select avg(sal) from emp);

--��������? 

--������ 3000 �̻� �޴� ����� �Ҽӵ� �μ�(10��, 20��)�� �����Ѻμ����� �ٹ��ϴ� ���
select ename,sal,deptno
from emp
where deptno in (select distinct deptno
from emp
where sal>=3000);


--���� ���� ������ �����÷����� �̵�
--where���� deptno�� ���μ��������� �����÷����� �̵�

select ename, sal, deptno
from emp e
where exists (select distinct deptno
                from emp
                where e.deptno = deptno and sal>=3000);

--�μ����� ���� �޿��� ���� �޴� ����� ����(��� ��ȣ, ����̸�, �޿�, �μ���ȣ)�� ����Ͻÿ�.(IN ������ �̿�)

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

--����(JOB)�� MANAGER�� ����� ���� �μ��� �μ� ��ȣ�� �μ���� ������ ����Ͻÿ�.
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




--���� ����� ���� �޿��� ���� �޴� ������� �̸��� �޿��� ����(��� ����)�� ����ϵ� ���� ����� ������� �ʽ��ϴ�.

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







