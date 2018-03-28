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







--2000���� 3000 ������ �޿��� �޴� ����� ��ȸ�� ���ô�.
SELECT *
FROM EMP
WHERE SAL >=2000 AND SAL<=3000;




--Ŀ�̼��� 300 �̰ų� 500 �Ǵ� 1400 �� ������� �����غ���
select *
from emp
where COMM=300 or comm=500 or comm=1400;


--EMPNO�� 7521 �̰ų� 7654 �̰ų� 7844 �� ������� ��� ��ȣ�� �޿��� �˻��ϴ� �������� �� �����ڿ� OR �� ������ ����Ͽ� �ۼ��Ͻÿ�.

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


--����� �̸��� K�� �����ϴ� ����� �����ȣ�� ����̸��� ����Ͻÿ�.
select * 
from emp
where ename like 'K%';

--�̸� �߿� K�� �����ϴ� ����� �����ȣ�� ����̸��� ����Ͻÿ�.
select * 
from emp
where ename like '%K%';

--�̸��� K�� ������ ����� �����ȣ�� ����̸��� ����Ͻÿ�.
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




--EMP ���̺��� �ڷḦ �Ի����� ������������ �����Ͽ� �ֱ� �Ի��� ������ ���� ����ϵ� �����ȣ, �����, ����, �Ի��� Į���� ����ϴ� �������� �ۼ��Ͻÿ�

select EMPNO, ENAME,JOB,HIREDATE 
from emp
order by hiredate asc;
--����Ʈ�� ��������
select EMPNO, ENAME,JOB,HIREDATE 
from emp
order by hiredate;

select empno,ename,job,hiredate,comm
from emp
order by comm asc;

--����
select empno,ename,job,hiredate,comm as ����
from emp
order by ���� asc;

select *
from emp
order by sal asc, ename asc;


--�μ� ��ȣ�� ���� ������� ����ϵ� ���� �μ����� ����� ����� ��� �ֱٿ� �Ի��� ������� ����ϵ��� �ϵ� ��� ��ȣ, �Ի���, ��� �̸�, �޿� ������ ����Ͻÿ�.
select EMPNO,HIREDATE,ENAME,SAL,DEPTNO
from emp
order by deptno asc, HIREDATE desc;



--1. �̸��� 'ö��'�� ���ԵǸ鼭 �޿��� 2000�̻��� ������ �̸��� �޿� ���(�� �޿��� ������ ����) //ö���� O�� ����
SELECT ENAME,SAL
FROM EMP
WHERE SAL>=2000 AND ENAME LIKE '%ö��%';

SELECT ENAME,SAL
FROM EMP
WHERE SAL>=2000 AND ENAME LIKE '%O%';

--2. 100% �λ�޿����� �Բ� ���(�� �λ�޿����� ������ ������)
SELECT ENAME,SAL,SAL*2 AS ������
FROM EMP
WHERE SAL>2000 AND ENAME LIKE '%ö��%';

SELECT ENAME,SAL,SAL*2 AS ������
FROM EMP
WHERE SAL>2000 AND ENAME LIKE '%O%';


--3. �μ���ȣ�� 10�Ǵ� 20�̸鼭 ������ NULL�� ������ �̸��� ���� ���(�� ������ �̸����� ��������)
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
