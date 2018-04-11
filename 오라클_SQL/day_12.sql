create user user03 identified by "oracle"
default tablespace myts
quota UNLIMITED on myts;


GRANT connect,resource 
to user03;


create table T100(
c number(6)
);

grant insert
on t100
to user03;

grant select 
on t100
to user03;


select *
from t100;


grant all
on t100
to user03;



DROP USER USER03 cascade;


ALTER USER USER03 IDENTIFIED BY "oracle03";

select Sid, Serial#, Username
   From V$session;
   
   alter system kill session '50,4803';


desc user_tables;

select table_name from user_tables
order by table_name desc;

desc all_tables;

select owner,table_name
from all_tables
order by owner,table_name desc;



select rownum, emp.*
from emp;

select rownum,emp.*
from emp
where rownum <=3;

select rownum,emp.*
from emp
where rownum <=3
order by emp.sal desc;


SELECT "급여 순위", ename,sal
FROM (SELECT ROWNUM "급여 순위", ename,sal
    from emp
    order by sal desc);


drop table emp;

CREATE TABLE EMP
       (EMPNO NUMBER(4) NOT NULL, -- 사번 컬럼(열) 수자료형(4자리수 0 ~9999) 값 필수
        ENAME VARCHAR2(10), -- 사원명 컬럼(열) 문자열(10바이트 영문숫자 1바이트 ,한글 3바이트) 
        JOB VARCHAR2(9),--업무
        MGR NUMBER(4),--상사사번
        HIREDATE DATE,--입사일 날짜형
        SAL NUMBER(7, 2), --급여
        COMM NUMBER(7, 2), --보너스
        DEPTNO NUMBER(2));--부서번호;
--신규 행(row, 레코드) 추가
INSERT INTO EMP VALUES
        (7369, 'SMITH',  'CLERK',     7902,
        TO_DATE('17-12-1980', 'DD-MM-YYYY'),  800, NULL, 20);
INSERT INTO EMP VALUES
        (7499, 'ALLEN',  'SALESMAN',  7698,
        TO_DATE('20-02-1981', 'DD-MM-YYYY'), 1600,  300, 30);
INSERT INTO EMP VALUES
        (7521, 'WARD',   'SALESMAN',  7698,
        TO_DATE('22-02-1981', 'DD-MM-YYYY'), 1250,  500, 30);
INSERT INTO EMP VALUES
        (7566, 'JONES',  'MANAGER',   7839,
        TO_DATE('2-08-1981', 'DD-MM-YYYY'),  2975, NULL, 20);
INSERT INTO EMP VALUES
        (7654, 'MARTIN', 'SALESMAN',  7698,
        TO_DATE('28-09-1981', 'DD-MM-YYYY'), 1250, 1400, 30);


INSERT INTO EMP VALUES
        (7698, 'BLAKE',  'MANAGER',   7839,
        TO_DATE('1-05-1981', 'DD-MM-YYYY'),  2850, NULL, 30);
INSERT INTO EMP VALUES
        (7782, 'CLARK',  'MANAGER',   7839,
        TO_DATE('9-06-1981', 'DD-MM-YYYY'),  2450, NULL, 10);
INSERT INTO EMP VALUES
        (7788, 'SCOTT',  'ANALYST',   7566,
        TO_DATE('09-12-1982', 'DD-MM-YYYY'), 3000, NULL, 20);
INSERT INTO EMP VALUES
        (7839, 'KING',   'PRESIDENT', NULL,
        TO_DATE('17-11-1981', 'DD-MM-YYYY'), 5000, NULL, 10);
INSERT INTO EMP VALUES
        (7844, 'TURNER', 'SALESMAN',  7698,
        TO_DATE('8-09-1981', 'DD-MM-YYYY'),  1500,    0, 30);
INSERT INTO EMP VALUES
        (7876, 'ADAMS',  'CLERK',     7788,
        TO_DATE('12-01-1983', 'DD-MM-YYYY'), 1100, NULL, 20);
INSERT INTO EMP VALUES
        (7900, 'JAMES',  'CLERK',     7698,
        TO_DATE('3-12-1981', 'DD-MM-YYYY'),   950, NULL, 30);
INSERT INTO EMP VALUES
        (7902, 'FORD',   'ANALYST',   7566,
        TO_DATE('3-12-1981', 'DD-MM-YYYY'),  3000, NULL, 20);
INSERT INTO EMP VALUES
        (7934, 'MILLER', 'CLERK',     7782,
        TO_DATE('23-01-1982', 'DD-MM-YYYY'), 1300, NULL, 10);
        
  
        
--SCOTT 출력 절차
set SERVEROUTPUT on
DECLARE
vename varchar2(20);
BEGIN
vename := 'SCOTT';
Dbms_Output.Put_Line(vename);
END;


DECLARE
vename varchar2(20);
BEGIN
select ename into vename
from emp
where empno = 7788;
Dbms_Output.Put_Line('사원명'||vename);
END;

DECLARE
vename emp.ename%type;
BEGIN
select ename into vename
from emp
where empno = 7788;
dbms_output.put_line('사원명 = '||vename);
end;



declare
vemp emp%rowtype;
BEGIN
select * into vemp
from emp
where ename = 'SCOTT';
Dbms_Output.Put_Line('사번 / 이름');
Dbms_Output.Put_Line(vemp.empno || '/' || vemp.ename);
end;




    SET SERVEROUTPUT ON
    DECLARE
        -- 레코드 타입을 정의
        TYPE emp_record IS RECORD(
            v_empno    emp.empno%TYPE,
            v_ename    emp.ename%TYPE,
            v_job    emp.job%TYPE,
            v_deptno  emp.deptno%TYPE);
        -- 레코드로 변수 선언
        emp_rec  emp_record;
            --사용할라면 결국 이렇게 해야 한다.
    BEGIN
        -- SCOTT 사원의 정보를 레코드 변수에 저장
        SELECT empno,ename, job, deptno
            INTO emp_rec
            FROM emp
            WHERE ename = UPPER('SCOTT');
        -- 레코드 변수에 저장된 사원 정보를 출력
        DBMS_OUTPUT.PUT_LINE('사원번호 : ' ||
            TO_CHAR(emp_rec.v_empno));
        DBMS_OUTPUT.PUT_LINE('이    름: ' ||
            emp_rec.v_ename);
        DBMS_OUTPUT.PUT_LINE('담당업무 : ' ||
            emp_rec.v_job);
        DBMS_OUTPUT.PUT_LINE('부서번호 : ' ||
            TO_CHAR(emp_rec.v_deptno));
    END;
    /
    
    /*
    1. 상품 테이블 생성
    (
    상품번호 문자(4) 기본키,
    상품명 문자(20),
    판매수량 숫자,
    가격 숫자
    )
    조건
    상품번호 형식 : P001 .. P999 (999개)
*/

delete from pnoodle;

drop table p;
create table pnoodle(
pnum varchar(4),
pname varchar(20) constraint pname_notnull not null,
sell number,
price number,
constraint pro_pk_pnum primary key(pnum));


--2. 상품번호는 자동증가컬럼으로 시퀀스가  입력
--    조건 :1..999(999개)

drop sequence nnum;
create sequence nnum
start with 1
increment by 1
maxvalue 999;

/*3. 임의의 4 개행  입력(상품번호 입력시 lpad() 적용)
    (예)
    insert into 상품
    values(concat('P',lpad(????),3,'0')),'진라면',5,100)
    라면은 진라면,신라면,너구리,팔도비빔면,짜파게티
*/
select * from pnoodle;



insert all
into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'진라면',90,500)
into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'신라면',69,450)
into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'너구리',60,550)
into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'팔도비빔면',89,600)
into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'짜파게티',37,650)
select * from dual;


insert into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'진라면',90,500);
insert into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'신라면',69,450);
insert into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'너구리',60,550);
insert into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'팔도비빔면',89,600);
insert into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'짜파게티',37,650);

select concat('P',lpad(nnum.nextval,3,0))
from dual;

select * from pnoodle;


select concat('P',lpad(to_char(nnum.nextval),3,0))
from dual;


/*4. 현재 총매출액을 구하여 inline_view 뷰로 정의
*/

select "총매출액"
from(select sum(sell*price) as "총매출액"
    from pnoodle);
    
    select pname,"총매출액"
from(select pname,sell*price as "총매출액"
    from pnoodle);
    
    
/*    5. 가장많이 팔린 2개 상품의 상품명,판매수량을
inline_view 뷰를 사용하여 출력(TOP 2)
*/

select rownum as "순위", p.pname,p.sell
from (select *
        from pnoodle
        order by sell desc) p
        where rownum <=2;
    


select *
from(select sell*price
    from pnoodle);



