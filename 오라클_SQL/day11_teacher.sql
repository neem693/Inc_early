CREATE INDEX IDX_EMP_ENAME
ON EMP(ENAME);

SELECT  EMPNO, ENAME 
FROM EMP 
--WHERE ENAME='FORD';
WHERE SUBSTR(ENAME,1,1)='F';
--uk,pk�÷��� �ε����� �ڵ����� ����
--�������̺�� ������ �ε��� ���̺�(�ε����÷� ENAME�� �ప�� ���ּ�(ROWID) ����) ����
--�ప�� ���ĵǾ� ����
--DQL �ӵ� ����(INDEX SCAN), DML ó���ӵ� ������(����� ������)
--INDEX SCAN�� ���Ұ�� FULL SCAN
SELECT 
   ROWID,ENAME
FROM EMP
ORDER BY ENAME ;

CREATE INDEX IDX_EMP01_JOB
ON EMP01(JOB);

--------------------------------------------------
CREATE INDEX IDX_EMP01_HIREDATE
ON EMP01(HIREDATE);
/***********************************************************
WHERE ���� ���� ���Ǵ� �÷��̰ų�
��ȸ����� ��ü ������ ������ 3-5% �̸��ΰ�� 
INDEX SCAN�� ȿ������
HIREDATE�� '20180410'�� ����
where HIREDATE='20180410'
--FULL SCAN (�ε����÷��� ���������� �Ǹ� �ε��� ������)
where to_char(HIREDATE,'yyyymmdd')='20180410'
->
--INDEX SCAN
where HIREDATE=to_date('20180410','yyyymmdd')

where substr(today,1,3)='ȭ����'
->
where today like'ȭ����%'

�� ����(TREE) ����
��������(��ã�� (������,������... ))

***********************************************************/
-- ������ DB (���̺����̽����� ����)
-- ���̺����̽��� DB��ü���� ���� ����
-- XE DB�� ���̺����̽�(���� = DBF) ����

-- system ����
create tablespace myts
datafile 'C:\oraclexe\app\oracle\oradata\XE\myts.dbf' size 10m
autoextend on next 50m maxsize 200m; 

create user user01 identified by "oracle" 
default tablespace myts 
quota UNLIMITED on myts;
--quota 10M on myts;

grant create session
to user01;



create table T01(
C number(6)
);
