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



--EMP 테이블을 복사하되 사원번호, 사원이름, 급여 컬럼으로 구성된 테이블을 생성하시오.(테이블의 이름은 EMP04 로 하시오.) 


CREATE TABLE EMP04
AS
SELECT EMPNO,ENAME,SAL FROM EMP;

select *
from dept;

--부서별로 가장 급여를 많이 받는 사원의 정보(사원 번호, 사원이름, 급여, 부서번호)를 출력하시오.(IN 연산자 이용)

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


--서브 쿼리문의 SELECT 문을 구성할 때 WHERE 절을 추가하여 원하는 조건을 제시하면 기존 테이블에서 일부의 행만 복사합니다.
create table emp05
as
select *
from emp
where deptno = 10;


desc emp05;

--DEPT 테이블과 동일한 구조의 빈 테이블 생성하기 생성하시오.(테이블의 이름은 DEPT02 로 하시오.)

CREATE TABLE DEPT02
AS
SELECT *
FROM DEPT
WHERE 1=0;


Alter table emp01
add (JOB VARCHAR(9));

desc emp01;

--DEPT02 테이블에 문자 타입의 부서장(DMGR) 칼럼을 추가해 봅시다. 

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


create table 점수(
번호 CHAR(5),
국어 NUMBER,
수학 NUMBER
);

alter table 점수
rename to SCORE;

desc score;
--숫자 3자리
alter table score
modify (kor number(3));

alter table score
modify (math number(3));

Insert into score
values('12345',100,90);


--------------------------------------------------


select kor + math as 합계
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

COMMENT ON COLUMN SCORE.BUNHO IS '학번';
COMMENT ON COLUMN SCORE.KOR IS '국어점수';
COMMENT ON COLUMN SCORE.MATH IS '수학점수';


desc score;

select *
from score;


create table product(
pid char(5),
pname varchar(20),
pnum number(5),
price number(7,2));


comment on column product.pnum is '판매수량';

comment on column product.pid is '상풍번호';


comment on column product.pname is '상풍명';


comment on column product.price is '가격';



