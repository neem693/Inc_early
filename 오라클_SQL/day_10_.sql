create sequence EMP_SEQ;

desc emp01;

drop table emp01;

create table emp01(
empno number(4) primary key,
ename varchar(10),
hiredate date
);

insert into emp01
values(emp_seq.nextval, 'JULIA' , sysdate);

select *
from emp01;



CREATE TABLE DEPT_EXAMPLE(
				DEPTNO NUMBER(4) PRIMARY KEY,
				DNAME VARCHAR2(15),
				LOC VARCHAR(15)
);

create sequence dept_example_seq
start with 10
increment by 10;

insert into dept_example values(dept_example_seq.nextval,'�λ��','����');

insert into dept_example values(dept_example_seq.nextval,'�渮��','����');

insert into dept_example values(dept_example_seq.nextval,'�ѹ���','����');

insert into dept_example values(dept_example_seq.nextval,'�����','��õ');


create index 

select ROWID
from emp01;

--1. EMP01 ���̺��� ���� �÷��� �ε����� �����ϵ� �ε��� �̸��� IDX_EMP01_JOB�� �ݽô�. 

select *
from emp01;

drop table emp01;

create table emp01
as
select * from emp;

create index IDX_EMP01_JOB
on EMP01(job);

SELECT *
FROM USER_IND_COLUMNS
WHERE TABLE_NAME IN('EMP01');


SELECT TABLE_NAME, INDEX_NAME, COLUMN_NAME 
FROM USER_IND_COLUMNS
WHERE TABLE_NAME IN('EMP', 'EMP01');


select hiredate
from emp
where hiredate = to_date('18-04-10','yy-mm-dd');

create index IDX_EMP01_HIREDATE
on EMP01(HIREDATE);

select ROWID, job
from emp01;


grant create session
to user01;

grant create table
to user01;

grant connect,resource to user01;

revoke create table from user01;

revoke dba from user01;

grant dba to user02;


revoke dba from user02;


grant connect,resource to user02;

