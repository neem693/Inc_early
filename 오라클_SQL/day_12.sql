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


SELECT "�޿� ����", ename,sal
FROM (SELECT ROWNUM "�޿� ����", ename,sal
    from emp
    order by sal desc);


drop table emp;

CREATE TABLE EMP
       (EMPNO NUMBER(4) NOT NULL, -- ��� �÷�(��) ���ڷ���(4�ڸ��� 0 ~9999) �� �ʼ�
        ENAME VARCHAR2(10), -- ����� �÷�(��) ���ڿ�(10����Ʈ �������� 1����Ʈ ,�ѱ� 3����Ʈ) 
        JOB VARCHAR2(9),--����
        MGR NUMBER(4),--�����
        HIREDATE DATE,--�Ի��� ��¥��
        SAL NUMBER(7, 2), --�޿�
        COMM NUMBER(7, 2), --���ʽ�
        DEPTNO NUMBER(2));--�μ���ȣ;
--�ű� ��(row, ���ڵ�) �߰�
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
        
  
        
--SCOTT ��� ����
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
Dbms_Output.Put_Line('�����'||vename);
END;

DECLARE
vename emp.ename%type;
BEGIN
select ename into vename
from emp
where empno = 7788;
dbms_output.put_line('����� = '||vename);
end;



declare
vemp emp%rowtype;
BEGIN
select * into vemp
from emp
where ename = 'SCOTT';
Dbms_Output.Put_Line('��� / �̸�');
Dbms_Output.Put_Line(vemp.empno || '/' || vemp.ename);
end;




    SET SERVEROUTPUT ON
    DECLARE
        -- ���ڵ� Ÿ���� ����
        TYPE emp_record IS RECORD(
            v_empno    emp.empno%TYPE,
            v_ename    emp.ename%TYPE,
            v_job    emp.job%TYPE,
            v_deptno  emp.deptno%TYPE);
        -- ���ڵ�� ���� ����
        emp_rec  emp_record;
            --����Ҷ�� �ᱹ �̷��� �ؾ� �Ѵ�.
    BEGIN
        -- SCOTT ����� ������ ���ڵ� ������ ����
        SELECT empno,ename, job, deptno
            INTO emp_rec
            FROM emp
            WHERE ename = UPPER('SCOTT');
        -- ���ڵ� ������ ����� ��� ������ ���
        DBMS_OUTPUT.PUT_LINE('�����ȣ : ' ||
            TO_CHAR(emp_rec.v_empno));
        DBMS_OUTPUT.PUT_LINE('��    ��: ' ||
            emp_rec.v_ename);
        DBMS_OUTPUT.PUT_LINE('������ : ' ||
            emp_rec.v_job);
        DBMS_OUTPUT.PUT_LINE('�μ���ȣ : ' ||
            TO_CHAR(emp_rec.v_deptno));
    END;
    /
    
    /*
    1. ��ǰ ���̺� ����
    (
    ��ǰ��ȣ ����(4) �⺻Ű,
    ��ǰ�� ����(20),
    �Ǹż��� ����,
    ���� ����
    )
    ����
    ��ǰ��ȣ ���� : P001 .. P999 (999��)
*/

delete from pnoodle;

drop table p;
create table pnoodle(
pnum varchar(4),
pname varchar(20) constraint pname_notnull not null,
sell number,
price number,
constraint pro_pk_pnum primary key(pnum));


--2. ��ǰ��ȣ�� �ڵ������÷����� ��������  �Է�
--    ���� :1..999(999��)

drop sequence nnum;
create sequence nnum
start with 1
increment by 1
maxvalue 999;

/*3. ������ 4 ����  �Է�(��ǰ��ȣ �Է½� lpad() ����)
    (��)
    insert into ��ǰ
    values(concat('P',lpad(????),3,'0')),'�����',5,100)
    ����� �����,�Ŷ��,�ʱ���,�ȵ������,¥�İ�Ƽ
*/
select * from pnoodle;



insert all
into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'�����',90,500)
into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'�Ŷ��',69,450)
into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'�ʱ���',60,550)
into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'�ȵ������',89,600)
into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'¥�İ�Ƽ',37,650)
select * from dual;


insert into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'�����',90,500);
insert into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'�Ŷ��',69,450);
insert into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'�ʱ���',60,550);
insert into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'�ȵ������',89,600);
insert into pnoodle(pnum,pname,sell,price)values(concat('P',lpad(nnum.nextval,3,0)),'¥�İ�Ƽ',37,650);

select concat('P',lpad(nnum.nextval,3,0))
from dual;

select * from pnoodle;


select concat('P',lpad(to_char(nnum.nextval),3,0))
from dual;


/*4. ���� �Ѹ������ ���Ͽ� inline_view ��� ����
*/

select "�Ѹ����"
from(select sum(sell*price) as "�Ѹ����"
    from pnoodle);
    
    select pname,"�Ѹ����"
from(select pname,sell*price as "�Ѹ����"
    from pnoodle);
    
    
/*    5. ���帹�� �ȸ� 2�� ��ǰ�� ��ǰ��,�Ǹż�����
inline_view �並 ����Ͽ� ���(TOP 2)
*/

select rownum as "����", p.pname,p.sell
from (select *
        from pnoodle
        order by sell desc) p
        where rownum <=2;
    


select *
from(select sell*price
    from pnoodle);



