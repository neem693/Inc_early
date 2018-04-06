CREATE TABLE dept01
    AS
        SELECT
            *
        FROM
            dept;

SELECT
    *
FROM
    dept01;

TRUNCATE TABLE dept01;

INSERT INTO dept01 (
    deptno,
    dname,
    loc
) VALUES (
    10,
    'ACCOUNTING',
    'NEW YORK'
);

INSERT INTO dept01 (
    deptno,
    dname,
    loc
) VALUES (
    15,
    '����',
    '����'
);

INSERT INTO dept01 VALUES (
    20,
    'RESEARCH',
    'DALLAS'
);

INSERT INTO dept01 VALUES (
    25,
    '������',
    '�λ�'
);

--���� �������� �̿��Ͽ� ������ ���� ������ SAM01 ���̺��� �����Ͻÿ�. ������ ��� DROP TABLE�� ���� �� �����Ͻÿ�.

CREATE TABLE sam01 (
    empno   NUMBER(4),
    ename   VARCHAR(10),
    job     VARCHAR(9),
    sal     NUMBER(7,2)
);

TRUNCATE TABLE sam01;

DROP TABLE sam01;

CREATE TABLE sam01
    AS
        SELECT
            empno,
            ename,
            job,
            sal
        FROM
            emp
        WHERE
            1 = 0;

DESC sam01;

--SAM01 ���̺� ������ ���� �����͸� �߰��Ͻÿ�.

INSERT INTO sam01 (
    empno,
    ename,
    job,
    sal
) VALUES (
    1000,
    'APPLE',
    'POLICE',
    10000
);

INSERT INTO sam01 (
    empno,
    ename,
    job,
    sal
) VALUES (
    1010,
    'BANANA',
    'NURSE',
    15000
);

INSERT INTO sam01 (
    empno,
    ename,
    job,
    sal
) VALUES (
    1020,
    'ORANGE',
    'DOCTOR',
    25000
);

SELECT
    *
FROM
    sam01;

INSERT INTO dept01 (
    deptno,
    dname
) VALUES (
    30,
    'SALES'
);

SELECT
    *
FROM
    dept01;

INSERT INTO dept01 (
    deptno,
    dname,
    loc
) VALUES (
    NULL,
    '',
    'NEW YORK'
);

INSERT INTO sam01 (
    empno,
    ename,
    job,
    sal
) VALUES (
    1030,
    'VERY',
    NULL,
    25000
);

INSERT INTO sam01 (
    empno,
    ename,
    job,
    sal
) VALUES (
    1030,
    'CAT',
    NULL,
    2000
);

SELECT
    *
FROM
    sam01;

INSERT INTO dept01
    SELECT
        *
    FROM
        dept;

INSERT ALL INTO dept01 VALUES (
    26,
    '������1',
    '�λ�'
) INTO dept01 VALUES (
    27,
    '������2',
    '�λ�'
) SELECT
    *
  FROM
    dual;

DROP TABLE emp01;

CREATE TABLE emp01
    AS
        SELECT
            *
        FROM
            emp;

--��� ����� �μ���ȣ�� 30������ �����սô�.

UPDATE emp01
    SET
        deptno = 30;
--�̹��� ��� ����� �޿��� 10% �λ��Ű�� UPDATE ���� ���ڽ��ϴ�.

UPDATE emp01
    SET
        sal = sal * 1.1;

--��� ����� �Ի����� ���÷� �����Ϸ��� ������ ���� �մϴ�.

UPDATE emp01
    SET
        hiredate = SYSDATE;

SELECT
    *
FROM
    emp01;

--�μ���ȣ�� 10���� ����� �μ���ȣ�� 30������ �����սô�.

TRUNCATE TABLE emp01;

INSERT INTO emp01
    SELECT
        *
    FROM
        emp;

UPDATE emp01
    SET
        deptno = 30
WHERE
    deptno = 10;
--�޿��� 3000 �̻��� ����� �޿��� 10% �λ��սô�. 

UPDATE emp01
    SET
        sal = sal * 1.1
WHERE
    sal > 3000;

--1987�⿡ �Ի��� ����� �Ի����� ���÷� �����սô�. ����� �Ի����� ���÷� ������ �Ŀ� ���̺� ������ ���캾�ô�.(���������ϰڴ�.)
--1982�⿡ �Ի��� ����� �Ի����� ���÷� �����սô�. ����� �Ի����� ���÷� ������ �Ŀ� ���̺� ������ ���캾�ô�.

UPDATE emp01
    SET
        hiredate = SYSDATE
WHERE
    substr(hiredate,1,2) = '82';

SELECT
    substr(hiredate,1,2)
FROM
    emp01;

--SAM01 ���̺� ����� ��� �� �޿��� 10000(��������= 5000) �̻��� ������� �޿��� 5000(�������� 500)���� �谨�Ͻÿ�.

SELECT
    *
FROM
    emp01;

UPDATE emp01
    SET
        sal = sal - 500
WHERE
    sal >= 5000;

--SCOTT ����� �μ���ȣ�� 20������, ������ MANAGER�� �Ѳ����� �����ϵ��� �սô�.

UPDATE emp01
    SET
        deptno = 20,
        job = 'MANAGER'
WHERE
    ename = 'SCOTT';

--SCOTT ����� �Ի����ڴ� ���÷�, �޿��� 50 ���� Ŀ�̼��� 4000 ���� �����սô�.

UPDATE emp01
    SET
        hiredate = SYSDATE,
        sal = 50,
        comm = 4000
WHERE
    ename = 'SCOTT';
--20�� �μ��� �������� 40�� �μ��� ���������� �����ϱ� ���ؼ� ���� �������� ����� ���ô�.

UPDATE dept01
    SET
        loc = (
            SELECT
                loc
            FROM
                dept
            WHERE
                deptno = 40
        )
WHERE
    deptno = 20;

SELECT
    *
FROM
    dept01;

SELECT
    *
FROM
    dept;

--�μ� ��ȣ�� 20���� �μ��� �̸��� ������ RESEARCH�� DALLAS�Դϴ�. ������ �μ���ȣ�� 20�� �μ��� �μ���� �������� �μ� ��ȣ�� 40���� �μ��� �����ϰ� �����ϱ� ���� UPDATE ��ɹ��Դϴ�.

DELETE FROM dept01 WHERE
    deptno = 30;

CREATE TABLE dept (
    deptno   NUMBER(2),--�μ���ȣ 
    dname    VARCHAR2(14),--�μ���
    loc      VARCHAR2(13)
);--�μ���ġ;

INSERT INTO dept VALUES (
    10,
    'ACCOUNTING',
    'NEW YORK'
);--�濵�繫ȸ���;

INSERT INTO dept VALUES (
    20,
    'RESEARCH',
    'DALLAS'
);--������;

INSERT INTO dept VALUES (
    30,
    'SALES',
    'CHICAGO'
);--������;

INSERT INTO dept VALUES (
    40,
    'OPERATIONS',
    'BOSTON'
);--�ѹ���;

SELECT
    *
FROM
    dept;

DELETE FROM emp01
WHERE
    deptno = (
        SELECT
            deptno
        FROM
            dept
        WHERE
            dname = 'SALES'
    );

--�μ� ��ȣ�� 20���� �μ��� �̸��� ������ RESEARCH�� DALLAS�Դϴ�. ������ �μ���ȣ�� 20�� �μ��� �μ���� 
--�������� �μ� ��ȣ�� 40���� �μ��� �����ϰ� �����ϱ� ���� UPDATE ��ɹ��Դϴ�.

UPDATE dept01
    SET
        ( dname,
        loc ) = (
            SELECT
                dname,
                loc
            FROM
                dept
            WHERE
                deptno = 40
        )
WHERE
    deptno = 20;


--�з��ڵ� ���̺� �����

CREATE TABLE �з��ڵ� (
    �з��ڵ尪   NUMBER(3),
    �ڵ��     VARCHAR(20),
    ��뿩��    VARCHAR(1)
);

--���� ���̺� �����

CREATE TABLE ���� (
    �����ڵ�    NUMBER(3),
    ������     VARCHAR(20),
    �⺻����    VARCHAR(4000),
    �󼼼���    CLOB,
    �����     DATE,
    �з��ڵ尪   NUMBER(3)
);

--������(���� �ڵ强) �����͸� �з��ڵ� ���̺� �߰�

INSERT all
INTO �з��ڵ� VALUES (100,  '����Ŭ',      'Y')
INTO �з��ڵ� VALUES (200,  '�ڹ�',       'Y')
INTO �з��ڵ� VALUES (300,  '�ȵ���̵�',    'Y')
select * from dual;

delete from �з��ڵ�;
select * from �з��ڵ�;


INSERT ;


--DB����(CRUD)
--SQL�� ���� (����)���α׷� ����
--�����ڵ�(�з��ڵ忡 �Ϸù�ȣ)

INSERT ALL  INTO ���� VALUES (101,    '����Ŭ1',     '�⺻',   '��',   '2017-06-24',   100) 
            INTO ���� VALUES (102,    '����Ŭ2',     '�⺻',   '��',   '2017-06-24',   100) 
            INTO ���� VALUES (201,    '�ڹ�1',       '�⺻',    '��',   '2017-06-25',  200) 
            INTO ���� VALUES (301,    '�ȵ���̵�1',  '�⺻',    '��',   '2017-06-24',  300)
SELECT *FROM dual;    
--'����Ŭ'�� ��� ������ �����ڵ�, ������, �⺻���� ���

SELECT
    �����ڵ�,
    ������,
    �⺻����
FROM
    ����
WHERE
    substr(�����ڵ�,1,1) = '1';
--where �����ڵ� between 100 and 199;
--where �з��ڵ尪 = 100;
--where �����ڵ� between 100 and 199;
--where �����ڵ� like '1__';
--where substr(�����ڵ�,1,1)= '1';

select *
from ����;

--�������� ���� ������ ���

SELECT
    c.�ڵ��,
    COUNT(*)
FROM
    ���� b
    INNER JOIN �з��ڵ� c ON b.�з��ڵ尪 = c.�з��ڵ尪
GROUP BY
    c.�ڵ��;
    
    SELECT
    *
FROM
    ���� b
    INNER JOIN �з��ڵ� c ON b.�з��ڵ尪 = c.�з��ڵ尪;
    
    --�� ���� ������ ����϶�(��, rollup�Լ� ����� ��)
    select decode(grouping(�з��ڵ尪),1,'�� ����',0,�з��ڵ尪) as "�з��ڵ�", count(*)
    from ����
    group by rollup(�з��ڵ尪);

select grouping(�з��ڵ尪),count(*)
from ����
group by rollup(�з��ڵ尪);

select nvl2(�з��ڵ尪,to_char(�з��ڵ尪),'�� ����') as "�з��ڵ�",count(*)
from ����
group by rollup(�з��ڵ尪);

create table �̱�����Ʈ_�׷�(
GR number(1),
EL CHAR(1));

insert all
into �̱�����Ʈ_�׷�("GR","EL") VALUES (1,'A')
into �̱�����Ʈ_�׷�("GR","EL") VALUES (1,'B')
into �̱�����Ʈ_�׷�("GR","EL") VALUES (1,'C')
into �̱�����Ʈ_�׷�("GR","EL") VALUES (2,'A')
into �̱�����Ʈ_�׷�("GR","EL") VALUES (3,'B')
into �̱�����Ʈ_�׷�("GR","EL") VALUES (4,'A')
into �̱�����Ʈ_�׷�("GR","EL") VALUES (4,'B')
into �̱�����Ʈ_�׷�("GR","EL") VALUES (5,'A')
into �̱�����Ʈ_�׷�("GR","EL") VALUES (5,'C')
select * from dual;

delete from �̱�����Ʈ_�׷�;

drop table �̱�����Ʈ_�׷�;

select *
from �̱�����Ʈ_�׷�;

select *
from �̱�����Ʈ_�׷� e inner join �̱�����Ʈ_�׷� f
on e.gr = f.gr;

select e.gr,e.el
from �̱�����Ʈ_�׷� e
where exists( select 1
              from �̱�����Ʈ_�׷�
              where gr = e.gr and
              el=e.el and
                    (el= 'A' OR el= 'B'));
select empno,ename,sal,deptno
from emp e
where exists (select max(sal)
from emp
group by deptno
having e.sal = max(sal));

--�����ڵ尡 301�� ������ �⺻������ '���� : ȫ�浿'���� ����


