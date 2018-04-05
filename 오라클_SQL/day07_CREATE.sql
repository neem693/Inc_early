CREATE TABLE EMP01( EMPNO NUMBER(4),
ENAME VARCHAR2(20),
SAL NUMBER(7,2));


desc emp01;

select * from emp01;


CREATE TABLE EMP02
AS
SELECT * FROM EMP;



desc emp02;

CREATE TABLE EMP03
AS
SELECT empno,ename FROM EMP;

desc emp03;



--EMP ���̺��� �����ϵ� �����ȣ, ����̸�, �޿� �÷����� ������ ���̺��� �����Ͻÿ�.(���̺��� �̸��� EMP04 �� �Ͻÿ�.) 


CREATE TABLE EMP04
AS
SELECT EMPNO,ENAME,SAL FROM EMP;

select *
from dept;

--�μ����� ���� �޿��� ���� �޴� ����� ����(��� ��ȣ, ����̸�, �޿�, �μ���ȣ)�� ����Ͻÿ�.(IN ������ �̿�)

select empno,ename,sal,deptno
from emp
where sal in(select max(sal)
from emp
group by deptno)
order by deptno;



select empno,ename,sal,deptno
from emp e
where exists (select max(sal)
from emp
group by deptno
having e.sal = max(sal));
---------------------------------------------------------


--���� �������� SELECT ���� ������ �� WHERE ���� �߰��Ͽ� ���ϴ� ������ �����ϸ� ���� ���̺��� �Ϻ��� �ุ �����մϴ�.
create table emp05
as
select *
from emp
where deptno = 10;


desc emp05;

--DEPT ���̺�� ������ ������ �� ���̺� �����ϱ� �����Ͻÿ�.(���̺��� �̸��� DEPT02 �� �Ͻÿ�.)

CREATE TABLE DEPT02
AS
SELECT *
FROM DEPT
WHERE 1=0;


Alter table emp01
add (JOB VARCHAR(9));

desc emp01;

--DEPT02 ���̺� ���� Ÿ���� �μ���(DMGR) Į���� �߰��� ���ô�. 

desc dept02;

alter table dept02
add (DMGR_NM VARCHAR(9));

desc dept02;

alter table dept02
drop column DMGR_NM;

alter table emp02
modify(JOB VARCHAR(30));

desc emp02;



alter table dept02
rename column dmgr to dmgr_nm;

alter table emp01
modify(job varchar(20));

desc emp01;

alter table emp01
modify(ename varchar);

alter table emp01
drop column job;

alter table dept02
rename to dept03;

TRUNCATE table emp02;
drop table emp02;


create table emp02
as select * from emp;


alter table emp02
set unused(comm,deptno);



select *
from emp02;

alter table emp02
drop unused Columns;


create table ����(
��ȣ CHAR(5),
���� NUMBER,
���� NUMBER
);

alter table ����
rename to SCORE;

desc score;
--���� 3�ڸ�
alter table score
modify (kor number(3));

alter table score
modify (math number(3));

Insert into score
values('12345',100,90);


--------------------------------------------------


select kor + math as �հ�
from score;

alter table score
add(total number(3));

truncate table score;

insert into score(bunho,kor,math)
values('12345',100,90);


insert into score
select '12345',100,90 from score;
select * from score;

update score
set total = kor+math
where total is null;



truncate table score;

COMMENT ON COLUMN SCORE.BUNHO IS '�й�';
COMMENT ON COLUMN SCORE.KOR IS '��������';
COMMENT ON COLUMN SCORE.MATH IS '��������';


desc score;

select *
from score;


create table product(
pid char(5),
pname varchar(20),
pnum number(5),
price number(7,2));


comment on column product.pnum is '�Ǹż���';

comment on column product.pid is '��ǳ��ȣ';


comment on column product.pname is '��ǳ��';


comment on column product.price is '����';



