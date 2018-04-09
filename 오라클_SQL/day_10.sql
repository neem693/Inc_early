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

--부모
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

--50은 DEPT06의 부서번호가 아님 오류
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

--자식의 부서번호 외래키관계 혹은 부서번호 행 제거 후 부모의 부서 번호 삭제

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

-- 뷰를 조회
select * from emp_view30;


desc emp_view30;


--기본 테이블은 EMP_COPY로 하여 20번 부서에 소속된 사원들의 사번과 이름과 부서번호와 상관의 사번을 출력하기 위한 
--SELECT문을 EMP_VIEW20 이란 이름의 뷰로 정의해 봅시다.

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

--시작 값이 1이고 1씩 증가하는 시퀀스 EMP_SEQ을 생성합니다. 

CREATE SEQUENCE DEPT_DEPTNO_SEQ_1
--INCREMENT BY 1
START WITH 1;

--4.이름에 M가 들어간 사원들의 이름,부서명,급여를 구하세요

select *
from dept;

select e.ename,d.dname,e.sal
from emp e inner join dept d
on e.deptno = d.deptno
where e.ename LIKE '%M%';


select count(*) 
from emp
where deptno = 30;

--3.최저 급여를 받는 사원과 같이 근무하는 모든 사원의 수와  최고 급여를 받는 사원과 같이 근무하는 모든 사원의 수를 출력
  

  select count(*) as "해당부서 사원 수"
  from emp e
where e.deptno = (select deptno
                from emp
                where sal = (select max(sal) from emp ));
                
select count(*) as "해당부서 사원 수"
from emp e
where e.deptno = (select deptno
                from emp
                where sal = (select min(sal) from emp ));



--2.chicago에서 근무하는 사원중 가장 많은 급여를 받는 사람보다 늦게 입사한 사람들의 성명,부서명,입사일자를 출력하시오
select ename,dname,hiredate
from emp inner join dept
on emp.deptno = dept.deptno
where hiredate > (select hiredate
                    from emp
                    where sal  = (select max(sal) 
                                    from emp inner join dept
                                    on emp.deptno = dept.deptno
                                    where dept.loc = 'CHICAGO'));
                                    
--1.MANAGER인 사원들의 봉급의 평균보다 봉급이 적은 사람들의 이름과 봉급을 출력하세요.

select * from emp;

select ename,sal
from emp
where sal < (select avg(sal)
                from emp
                where job = 'MANAGER'
                group by job);
                
                
                
 --문제 3
 --9.커미션 계약조건이 있는 사원중 이름에 'N'자가 들어가는 사원의 최소급여를 받는 사원의 사원명, 부서명을 출력하세요.
 
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


--8.DALLAS에 위치한 부서에 속한 사원의 총사원수,평균급여,전체급여,최고급여,최저급여을 구하세요.
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
                
                



  