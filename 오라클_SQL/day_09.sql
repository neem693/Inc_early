update 도서 set 기본설명 = '저자:홍길동' where 도서코드 = 301;

delete  from 도서 where 도서코드 = 301;
rollback;

insert into 도서 values(301,'안드로이드','저자:홍길동','상세','17-06-26',300);

select * from 도서;
desc 도서;


update 도서
set "기본설명" = ' 저자 : ABC'
where "도서코드" = 301;

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

select * from 도서;

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
bno number(3) primary key, --도서번호
bname varchar2(20), 
bdesc varchar2(4000),
bprice number);

create table MEM(
MEMID VARCHAR(20) primary key,
MEMNAME VARCHAR(20), --주문자ID
MEMADDR VARCHAR(20));


create table border(
ONO number primary keyujm, --주문번호(1,2...일련변호)
BNO NUMBER, -- 주문도서번호
MEMID VARCHAR(20),  -- 주문자ID
OQTY NUMBER); -- 주문도서수량

select * from book;
select * from mem;
insert all
into BOOK VALUES( 101,'오라클1','기본',1000)
into BOOK VALUES( 102,'오라클2','기본',2000)
into BOOK VALUES( 201,'자바1','기본',2000)
select * from dual;

delete from mem;

insert all
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('hong','홍자바','서울')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('eif','엘프','천안')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('ioioe','이오이다','청주')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('efdie','탱스자','평택')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('orat','오라트리','서울')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('gkeo','개코','전주')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('defconn','장노','LA')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('wied','가고','뉴욕')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('howdare','감히','파리')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('ioi','DAVID','파리')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('doef','똥파리','런던')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('wks','미사토','도쿄')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('eofi','밤주','베이징')
select * from dual;


commit;



select *
from mem;

select *
from border;

select *
from book;



--문제
--'홍자바'가 도서번호 101인 도서2권 주문(INSERT)

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

--주문한 회원명 및 도서명을 주문수량과 함께 출력(조인)

select mem.memname,book.bname,border.oqty
from mem inner join border
on mem.memid =border.memid
inner join book
on book.bno = border.bno;





--주문한 총도서수량을 도서명(혹은 도서번호)별로 그룹지어 출력(조인)




select bname,sum(oqty)
from book , border
where book.bno = border.bno
group by rollup(book.bname);

select *
from book , border
where book.bno = border.bno;

--주문한 총도서수량을 도서명(혹은 도서번호)/회원별로 그룹지어 출력(조인)

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
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('hong','홍자바','서울')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('eif','엘프','천안')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('ioioe','이오이다','청주')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('efdie','탱스자','평택')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('orat','오라트리','서울')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('gkeo','개코','전주')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('defconn','장노','LA')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('wied','가고','뉴욕')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('eodi','감히','파리')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('ioi','DAVID','파리')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('doef','똥파리','런던')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('wks','미사토','도쿄')
into MEM(MEMID,MEMNAME,MEMADDR) VALUES('eofi','밤주','베이징')
select * from dual;





