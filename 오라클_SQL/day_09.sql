update ���� set �⺻���� = '����:ȫ�浿' where �����ڵ� = 301;

delete  from ���� where �����ڵ� = 301;
rollback;

insert into ���� values(301,'�ȵ���̵�','����:ȫ�浿','��','17-06-26',300);

select * from ����;
desc ����;


update ����
set "�⺻����" = ' ���� : ABC'
where "�����ڵ�" = 301;

create table dept01
as
select * from dept;

desc dept01;


select * from dept01;


delete from dept01;

select * from dept01;

rollback;

desc dept01;

delete from dept01
where deptno= 20;

select * from dept01;

rollback;

select * from ����;

DELETE FROM DEPT01 WHERE DEPTNO=40;

select * from dept01;

select * from emp01;
delete  from emp01;
drop table emp01;

create table emp01 
as
select * from emp;




update emp01
set sal=300
where ename = 'SCOTT';
rollback;


select sal
from emp01
where ename = 'SCOTT'
for update of sal wait 5;



drop table emp02;

CREATE TABLE EMP02( 
EMPNO NUMBER(4) NOT NULL, 
ENAME VARCHAR2(10) NOT NULL, 
JOB VARCHAR2(9),
DEPTNO NUMBER(2)
); 


insert into emp02
values(NULL,NULL,'SALESMAN',10);

select * from emp02;


drop table emp03;

create table emp03(
EMPNO NUMBER(4) UNIQUE, 
ENAME VARCHAR2(10) NOT NULL, 
JOB VARCHAR2(9),
DEPTNO NUMBER(2)
);
delete from emp03;
insert into emp03
values(NULL,'SDIED','SALESMAN',10);


delete from emp03;
insert all
into emp03 values(2932,'SMITH','SALESMAN',10)
into emp03 values(2932,'DAVID','ORIGIN',20)
select * from dual;


select *
from emp03;


rollback;


select *
from emp03;


drop table emp05;

CREATE TABLE EMP05( 
EMPNO NUMBER(4) PRIMARY KEY ,
ENAME VARCHAR2(10)  NOT NULL, 
JOB VARCHAR2(9) ,
DEPTNO NUMBER(2)
); 


insert into emp05  values(7499,'ALLEN','SALESMAN',30);
insert into emp05  values(7499,'ALLEN','SALESMAN',30);
insert into emp05  values(NULL,'ALLEN','SALESMAN',30);


create table book(
bno number(3) primary key, --������ȣ
bname varchar2(20), 
bdesc varchar2(4000),
bprice number);

create table MEM(
MEMID VARCHAR(20) primary key,
MEMNAME VARCHAR(20), --�ֹ���ID
MEMADDR VARCHAR(20));


create table border(
ONO number primary keyujm, --�ֹ���ȣ(1,2...�Ϸú�ȣ)
BNO NUMBER, -- �ֹ�������ȣ
MEMID VARCHAR(20),  -- �ֹ���ID
OQTY NUMBER); -- �ֹ���������

select * from book;
select * from mem;
insert all
into BOOK VALUES( 101,'����Ŭ1','�⺻',1000)
into BOOK VALUES( 102,'����Ŭ2','�⺻',2000)
into BOOK VALUES( 201,'�ڹ�1','�⺻',2000)
select * from dual;

delete from mem;

insert all
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('hong','ȫ�ڹ�','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('eif','����','õ��')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('ioioe','�̿��̴�','û��')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('efdie','�ʽ���','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('orat','����Ʈ��','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('gkeo','����','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('defconn','���','LA')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('wied','����','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('howdare','����','�ĸ�')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('ioi','DAVID','�ĸ�')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('doef','���ĸ�','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('wks','�̻���','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('eofi','����','����¡')
select * from dual;


commit;



select *
from mem;

select *
from border;

select *
from book;



--����
--'ȫ�ڹ�'�� ������ȣ 101�� ����2�� �ֹ�(INSERT)

delete from border;


insert into border(ONO,BNO,MEMID,OQTY)
select count(*)+1,101,'hong',2
from border;

insert into border(ONO,BNO,MEMID,OQTY)
select count(*)+1,201,'eif',3
from border;

insert into border(ONO,BNO,MEMID,OQTY)
select count(*)+1,102,'ioioe',1
from border;


insert into border(ONO,BNO,MEMID,OQTY)
select count(*)+1,101,'efdie',1
from border;

insert into border(ONO,BNO,MEMID,OQTY)
select count(*)+1,201,'defconn',1
from border;

insert into border(ONO,BNO,MEMID,OQTY)
select count(*)+1,201,'wks',1
from border;


insert into border(ONO,BNO,MEMID,OQTY)
select count(*)+1,201,'ioi',1
from border;


insert into border(ONO,BNO,MEMID,OQTY)
select count(*)+1,101,'eif',1
from border;

rollback;

commit;

--�ֹ��� ȸ���� �� �������� �ֹ������� �Բ� ���(����)

select mem.memname,book.bname,border.oqty
from mem inner join border
on mem.memid =border.memid
inner join book
on book.bno = border.bno;





--�ֹ��� �ѵ��������� ������(Ȥ�� ������ȣ)���� �׷����� ���(����)




select bname,sum(oqty)
from book , border
where book.bno = border.bno
group by rollup(book.bname);

select *
from book , border
where book.bno = border.bno;

--�ֹ��� �ѵ��������� ������(Ȥ�� ������ȣ)/ȸ������ �׷����� ���(����)

select book.bname,mem.memid,sum(oqty)
from mem inner join border
on mem.memid =border.memid
inner join book
on book.bno = border.bno
group by book.bname,mem.memid
order by book.bname;





select mem.memname,book.bname,sum(oqty)
from mem inner join border
on mem.memid =border.memid
inner join book
on book.bno = border.bno
group by book.bname,mem.memname
order by book.bname;






select book.bname,mem.memname,sum(oqty)
from mem inner join border
on mem.memid =border.memid
inner join book
on book.bno = border.bno
group by book.bname,mem.memname
order by book.bname,mem.memname;







insert all
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('hong','ȫ�ڹ�','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('eif','����','õ��')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('ioioe','�̿��̴�','û��')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('efdie','�ʽ���','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('orat','����Ʈ��','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('gkeo','����','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('defconn','���','LA')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('wied','����','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('eodi','����','�ĸ�')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('ioi','DAVID','�ĸ�')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('doef','���ĸ�','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('wks','�̻���','����')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('eofi','����','����¡')
select * from dual;





