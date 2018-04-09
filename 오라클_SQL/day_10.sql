CREATE TABLE EMP05( 
EMPNO NUMBER(4) PRIMARY KEY ,
ENAME VARCHAR2(10)  NOT NULL, 
JOB VARCHAR2(9) unique,
DEPTNO NUMBER(2)
); 


drop table emp05;


insert into emp05  values(7499,'ALLEN','SALESMAN',30);
insert into emp05  values(7499,'ALLEN','SALESMAN',30);
insert into emp05  values(NULL,'ALLEN','SALESMAN',30);


delete from emp04;
commit;
drop table emp04;

CREATE TABLE EMP04(
empno number(4) constraint EMP04_EMPNO_UK UNIQUE,
ENAME VARCHAR(10) CONSTRAINT EMP04_ENAME_NN NOT NULL,
JOB VARCHAR(9),
DEPTNO NUMBER(2)
);


INSERT INTO EMP04
VALUES(7499,'ALLEN','SALESMAN',30);

delete from emp05;

drop table emp05;

CREATE TABLE EMP05( 
EMPNO NUMBER(4) CONSTRAINT EMP05_EMPNO_PK PRIMARY KEY ,
ENAME VARCHAR2(10) CONSTRAINT EMP05_ENAME_NN NOT NULL, 
JOB VARCHAR2(9) constraint EMP05_JOB_UK unique constraint EMP05_JOB_NN not null,
DEPTNO NUMBER(2)
); 

drop table emp03;


CREATE TABLE EMP03( 
EMPNO NUMBER(4) CONSTRAINT EMP03_ENAME_NN NOT NULL,
ENAME VARCHAR2(10), 
JOB VARCHAR2(9),
DEPTNO NUMBER(4),
CONSTRAINT EMP03_EMPNO_PK PRIMARY KEY(EMPNO,ENAME),
CONSTRAINT EMP03_JOB_UK UNIQUE(JOB)
); 



drop table emp06;

--�θ�
CREATE TABLE DEPT06
AS
SELECT * FROM DEPT;

CREATE TABLE EMP06( 
EMPNO NUMBER(4) 
CONSTRAINT EMP06_EMPNO_PK PRIMARY KEY ,
ENAME VARCHAR2(10) 
CONSTRAINT EMP06_ENAME_NN NOT NULL, 
JOB VARCHAR2(9),
DEPTNO NUMBER(2)
CONSTRAINT EMP06_DEPTNO_FK REFERENCES DEPT06(DEPTNO)
); 



ALTER TABLE DEPT06 ADD CONSTRAINT DEPT06_DEPTNO_PK PRIMARY KEY(DEPTNO);

INSERT INTO EMP06
values(4568,'JONES','SALESMAN',10);

--50�� DEPT06�� �μ���ȣ�� �ƴ� ����
INSERT INTO EMP06
values(1234,'JONES2','SALESMAN',50);


CREATE TABLE EMP07( 
EMPNO NUMBER(4) 
CONSTRAINT EMP07_EMPNO_PK PRIMARY KEY ,
ENAME VARCHAR2(10)
CONSTRAINT EMP07_ENAME_NN NOT NULL, 
SAL NUMBER(7, 2)
CONSTRAINT EMP07_SAL_CK CHECK(SAL BETWEEN 500 AND 5000),
GENDER VARCHAR2(1) 
CONSTRAINT EMP07_GENDER_CK CHECK (GENDER IN('M', 'F'))
);

insert into emp07
values(1,'A',1000,'M');

select * from emp07;

select * from dept;

rollback;

insert into dept values(10,'ACCOUNTING','NEW YORK');


CREATE TABLE DEPT07
       (DEPTNO NUMBER(2) CONSTRAINT DEPT_CODE_PK PRIMARY KEY,
        DNAME VARCHAR2(14)NOT NULL,
        LOC VARCHAR2(13) DEFAULT 'SEOUL',
        CONSTRAINT CK CHECK (DEPTNO IN(10,20,30,40)
        ));

drop table dept07;

insert into dept07 values(10,'ACCOUNTING','NEW YORK');


insert into dept07(deptno,dname)
values(20,'ACCOUNTING2');
rollback;

select * from dept07;

insert into dept07 (deptno,dname,loc)
values(30,'ACCOUNTING2',null);

alter table dept07
drop constraint ck;

alter table dept07
drop primary key;


alter table dept07
drop not null;

--�ڽ��� �μ���ȣ �ܷ�Ű���� Ȥ�� �μ���ȣ �� ���� �� �θ��� �μ� ��ȣ ����

create table EMP_COPY
as
select * from emp;


create view EMP_VIEW30
as
select empno,ename,deptno
from emp_copy
where deptno=30;


select *
from emp_copy;

-- �並 ��ȸ
select * from emp_view30;


desc emp_view30;


--�⺻ ���̺��� EMP_COPY�� �Ͽ� 20�� �μ��� �Ҽӵ� ������� ����� �̸��� �μ���ȣ�� ����� ����� ����ϱ� ���� 
--SELECT���� EMP_VIEW20 �̶� �̸��� ��� ������ ���ô�.

create view EMP_VIEW20
as
select empno,ename,deptno
from emp
where deptno = 20;

select * from emp_view30;

create or replace view emp_view30
as
select empno,ename,deptno
from emp
where deptno = 20
with read only;

drop view emp_view20;
drop table emp_copy;

insert into emp_view30
values(8000,'ANGEL',30);


desc USER_CONSTRAINTS;

select *
from user_constraints
order by last_change desc;

select constraint_name,constraint_type,table_name,search_condition,last_change
from user_constraints
order by last_change desc;


CREATE SEQUENCE DEPT_DEPTNO_SEQ
INCREMENT BY 10
START WITH 10;

--���� ���� 1�̰� 1�� �����ϴ� ������ EMP_SEQ�� �����մϴ�. 

CREATE SEQUENCE DEPT_DEPTNO_SEQ_1
--INCREMENT BY 1
START WITH 1;

--4.�̸��� M�� �� ������� �̸�,�μ���,�޿��� ���ϼ���

select *
from dept;

select e.ename,d.dname,e.sal
from emp e inner join dept d
on e.deptno = d.deptno
where e.ename LIKE '%M%';


select count(*) 
from emp
where deptno = 30;

--3.���� �޿��� �޴� ����� ���� �ٹ��ϴ� ��� ����� ����  �ְ� �޿��� �޴� ����� ���� �ٹ��ϴ� ��� ����� ���� ���
  

  select count(*) as "�ش�μ� ��� ��"
  from emp e
where e.deptno = (select deptno
                from emp
                where sal = (select max(sal) from emp ));
                
select count(*) as "�ش�μ� ��� ��"
from emp e
where e.deptno = (select deptno
                from emp
                where sal = (select min(sal) from emp ));



--2.chicago���� �ٹ��ϴ� ����� ���� ���� �޿��� �޴� ������� �ʰ� �Ի��� ������� ����,�μ���,�Ի����ڸ� ����Ͻÿ�
select ename,dname,hiredate
from emp inner join dept
on emp.deptno = dept.deptno
where hiredate > (select hiredate
                    from emp
                    where sal  = (select max(sal) 
                                    from emp inner join dept
                                    on emp.deptno = dept.deptno
                                    where dept.loc = 'CHICAGO'));
                                    
--1.MANAGER�� ������� ������ ��պ��� ������ ���� ������� �̸��� ������ ����ϼ���.

select * from emp;

select ename,sal
from emp
where sal < (select avg(sal)
                from emp
                where job = 'MANAGER'
                group by job);
                
                
                
 --���� 3
 --9.Ŀ�̼� ��������� �ִ� ����� �̸��� 'N'�ڰ� ���� ����� �ּұ޿��� �޴� ����� �����, �μ����� ����ϼ���.
 
select ename,dname
from emp inner join dept
on emp.deptno = dept.deptno
where comm is not null and
ename like '%N%' and sal =(select min(sal)
            from emp e
            where exists (select 1
                from emp
                where e.empno = empno
                and ename LIKE '%N%'
                and comm is not null));


--8.DALLAS�� ��ġ�� �μ��� ���� ����� �ѻ����,��ձ޿�,��ü�޿�,�ְ�޿�,�����޿��� ���ϼ���.
select count(*),avg(sal),sum(sal),max(sal),min(sal)
from emp e
where exists (select 1
                from emp inner join dept
                on emp.deptno = dept.deptno
                where e.empno = empno
                and loc = 'DALLAS');



SELECT COUNT(*),AVG(SAL),SUM(SAL),MAX(SAL),MIN(SAL) FROM (
    SELECT SAL FROM EMP E,DEPT D
    WHERE E.DEPTNO=D.DEPTNO
    AND E.DEPTNO=(
        SELECT DEPTNO FROM DEPT WHERE LOC='DALLAS'
    )
)
                
                



  