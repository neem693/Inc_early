 --Join: ���̺� ����(�� ���� �� �� ����)
--1. ����(inner)���� 
--Cross Join
SELECT
    *
FROM EMP,dept;
-- Equi Join(��ġ,�,�׵�) :�����÷��� ���� ��ġ�ϴ� �� ����
SELECT *
FROM EMP, DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO;
-- ���������ϸ� ������
SELECT e.ENAME, d.DNAME,e.DEPTNO
FROM EMP e, DEPT d
WHERE e.DEPTNO=d.DEPTNO
             AND e.ENAME='SCOTT';
             
  desc SALGRADE;
  SELECT
      *
  FROM SALGRADE;
 --Non-Equi Join (Equi Join���ؼ� ���� ���X)
  SELECT ENAME, SAL, GRADE
FROM EMP, SALGRADE 
WHERE SAL BETWEEN LOSAL AND HISAL;
  
 SELECT e.ENAME, d.DNAME,e.DEPTNO
FROM EMP e, DEPT d
WHERE e.DEPTNO<>d.DEPTNO; 
-----------------------------
--2.�ܺ�����
--���� �ܺ�����
--����� ����(null) �μ�(40�� OPERATIONS)�� ���� ���
--null�� ���̺� �����÷��� (+) 
 SELECT e.ENAME, d.DNAME,e.DEPTNO
FROM EMP e, DEPT d
WHERE e.DEPTNO(+)=d.DEPTNO;
--�׹ۿ� ������ �ܺ�����,���� �ܺ�����
-------------------
--UNION ������(�ິ��) : �ߺ����(������)�� ����
--UNION ALL ������(�ິ��) : �ߺ����(������)�� ����
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
--3. �������� : �ڽ��� �ٸ� ������ �ְ� ���� 
--�����÷����� �޶� ��
select employee.ename|| '�� �Ŵ�����'|| manager.ename||'�Դϴ�' 	 
from emp employee, emp manager
	     where employee.mgr = manager.empno and EMPLOYEE.ENAME='SMITH';


SELECT DEPTNO
FROM emp
WHERE ENAME='SCOTT';
------------------------------
SELECT ENAME
FROM emp
WHERE DEPTNO=20;
--e2.ENAME ���� �μ������
SELECT e.ENAME,e2.ENAME
FROM emp e,emp e2
WHERE e.DEPTNO=e2.DEPTNO
and e.ENAME='SCOTT'
and E.ENAME <> E2.ENAME;
----------------------------------
-- �μ��� ��׷��� ���޺� �ұ׷����� �з� ����(�Ű����� ���� �߿�) : �����м�
SELECT DEPTNO,job, SUM(sal)
     FROM emp
     GROUP BY  DEPTNO,job;
     
-- �μ��� ��׷��� ���޺� �ұ׷����� �з� ����(�Ű����� ���� �߿�) : �����м� �� �Ұ�,���հ� 
SELECT DEPTNO,job, SUM(sal)
     FROM emp
     GROUP BY ROLLUP( DEPTNO,job);
-- ROLLUP + ���޺� �ұ׷����� �з� �Ұ� : �����м� 
     SELECT DEPTNO,job, SUM(sal)
     FROM emp
     GROUP BY CUBE(DEPTNO,job);
-- �÷����� �ܼ� ����     
     SELECT DEPTNO,job, SUM(sal)
     FROM emp
     GROUP BY GROUPING SETS (DEPTNO,job);
             