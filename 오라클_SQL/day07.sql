--SCOTT ���� �Ի����� ���� ����� ���� �μ����� �ٹ��ϴ� ������� �μ���,�̸�,�޿�
--�ʴ´ٴ� �� �� ���ٴ� �� �ǹ��Ѵ�.
--��, SCOTT���� �� ���� HIREDATE�� �䱸�ȴ�.
select deptno,ename,sal
from emp
where hiredate > (select hiredate
from emp
where ename = 'SCOTT');

--���� ���̺��� ���������� �����Ƿ� ���������� �Ѵ�.

select f.deptno,f.ename,f.sal
from emp e inner join emp f
on e.hiredate<f.hiredate
where e.ename = 'SCOTT'and
e.ename <> f.ename;

desc salgrade;

--SCOTT�� ���,�μ���,�޿�,�޿���� ���
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




