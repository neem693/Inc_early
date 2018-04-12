SET SERVEROUTPUT ON;
DECLARE
   VEMPNO 	 NUMBER(4);
   VENAME 	 VARCHAR2(20);
   VDEPTNO       EMP.DEPTNO%TYPE;
   VDNAME 	 VARCHAR2(20) := NULL;
BEGIN
 SELECT EMPNO, ENAME, DEPTNO INTO VEMPNO,
               VENAME, VDEPTNO 
 FROM  EMP
 WHERE EMPNO=7788;

 
 IF (VDEPTNO = 10)  THEN
      VDNAME := 'ACCOUNTING';
 END IF;
 IF(VDEPTNO = 20) THEN 
 VDNAME := '������'; 
 END IF;
 IF (VDEPTNO = 30)  THEN
      VDNAME := 'SALES';
 END IF;
 IF (VDEPTNO = 40) THEN  
      VDNAME := 'OPERATIONS';
 END IF;

 DBMS_OUTPUT.PUT_LINE('���    �̸�    �μ���');
 DBMS_OUTPUT.PUT_LINE(VEMPNO||'    '||VENAME
  ||'    '||VDNAME);
END;
/



--������ ������ ���ϴ� �����Դϴ�. ���� ����� ���� ���޿�*12+Ŀ�̼ǡ��̶� ������ ����ϰڽ��ϴ�. (CH20_QUIZ_01.sql)

declare
    vemp emp%ROWTYPE;
BEGIN
    select * into vemp
    from emp
    where ename = 'SCOTT';
    vemp.comm := nvl(vemp.comm,0);
    DBMS_OUTPUT.PUT_LINE('�������');
    DBMS_OUTPUT.PUT_LINE((vemp.sal * 12)  + vemp.comm);
END;    


SET SERVEROUTPUT ON
DECLARE
  VEMP EMP%ROWTYPE;
  ANNSAL NUMBER(7,2);
BEGIN

  SELECT * INTO VEMP
  FROM EMP
  WHERE ENAME='SCOTT';
IF (VEMP.COMM IS NULL)THEN      
    ANNSAL:=VEMP.SAL*12;          
  ELSE                            
    ANNSAL:=VEMP.SAL*12+VEMP.COMM;
  END IF;
  DBMS_OUTPUT.PUT_LINE('��� / �̸� / ����'); 
  DBMS_OUTPUT.PUT_LINE('----------------------');
  DBMS_OUTPUT.PUT_LINE(VEMP.EMPNO||'/'||VEMP.ENAME||'/'||ANNSAL); 
END;
/

SET SERVEROUTPUT ON
DECLARE
    VEMP EMP%ROWTYPE;
    VDNAME VARCHAR2(14);
BEGIN
    DBMS_OUTPUT.PUT_LINE('��� / �̸� / �μ���');
    DBMS_OUTPUT.PUT_LINE('--------------------');
    SELECT * INTO VEMP
    FROM EMP
    WHERE ENAME='SCOTT';
    IF (VEMP.DEPTNO = 10)  THEN
        VDNAME := 'ACCOUNTING';
    ELSIF (VEMP.DEPTNO = 20)  THEN
        VDNAME := 'RESEARCH';
ELSIF (VEMP.DEPTNO = 30)  THEN
    VDNAME := 'SALES';
    ELSIF (VEMP.DEPTNO = 40) THEN
    VDNAME := 'OPERATIONS';
    ELSIF (vemp.deptno =50) THEN
    VDNAME := '���ߺ�';
    END IF;
    DBMS_OUTPUT.PUT_LINE(VEMP.EMPNO||'/'||
    VEMP.ENAME||'/'||VDNAME);
END;


SET SERVEROUTPUT ON
DECLARE
BEGIN
FOR N IN REVERSE 1..5 LOOP
DBMS_OUTPUT.PUT_LINE( N );
END LOOP;
END;


--������WHILE LOOP ������ ���� �ﰢ�� ������ ����ϴ� ���Դϴ�. �� ������ ä��ÿ�.(CH20_QUIZ_04.sql)

declare
g number;
str varchar(20);
ret varchar(40);
begin
str:='ABCDEFG';
g :=LENGTH(str);
for n in 1..g LOOP
ret:= ret||'*';
DBMS_OUTPUT.PUT_LINE(ret);
end loop;
end;

declare
x number;
g number;
str varchar(20);
ret varchar(40);
spac varchar(20);
begin
str:='ABCDEFG';
g :=LENGTH(str);
for n in 1..g LOOP
spac = ' ';
ret:= ret||'*';
DBMS_OUTPUT.PUT_LINE(ret);
end loop;
end;


decalre
begin
for k IN (select rownum, ename,job from emp) loop
dbms_output.put_line(k.rownum||'. '|| K.ename || ' /' || k.job);
end loop;
end;

declare
VDEPT DEPT%ROWTYPE;
CURSOR C1 
IS
SELECT * FROM DEPT;
BEGIN
DBMS_OUTPUT.PUT_LINE('�μ���ȣ / �μ��� / ������'); 
DBMS_OUTPUT.PUT_LINE('----------------------------');
FOR VDEPT IN C1 LOOP
EXIT WHEN C1%NOTFOUND;
DBMS_OUTPUT.PUT_LINE(VDEPT.DEPTNO||
' '||VDEPT.DNAME||' '||VDEPT.LOC); 
END LOOP;
END;


--��� ������� ����ϰ� �� ���� ������� ���� �� ��ŭ * ���

declare
n number;
ret1 varchar(100);
begin
for k IN (select rownum, ename,job from emp) loop
n := length(k.ename);
ret1:='';
for i in 1..n loop
ret1:=ret1||'*';
end loop;
dbms_output.put_line(k.ename || ' / ' || ret1 );
end loop;
end;

declare
n number;
ret1 varchar(100);
begin


for k IN (select rownum, ename,job from emp) loop
ret1 := '';
n := length(k.ename);



for i in 1..n loop
ret1:=ret1||'*';
end loop;


dbms_output.put_line(k.ename || ' / ' || ret1 );
end loop;
end;


declare
n number;
ret1 varchar(100);
begin
for k IN (select rownum, ename,job from emp) loop
ret1 := '';
n := length(k.ename);
ret1 := rpad(' ',n+1,'*');
dbms_output.put_line(k.ename || ' / ' || ret1 );
end loop;
end;




