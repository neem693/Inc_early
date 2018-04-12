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
 VDNAME := '연구부'; 
 END IF;
 IF (VDEPTNO = 30)  THEN
      VDNAME := 'SALES';
 END IF;
 IF (VDEPTNO = 40) THEN  
      VDNAME := 'OPERATIONS';
 END IF;

 DBMS_OUTPUT.PUT_LINE('사번    이름    부서명');
 DBMS_OUTPUT.PUT_LINE(VEMPNO||'    '||VENAME
  ||'    '||VDNAME);
END;
/



--다음은 연봉을 구하는 예제입니다. 연봉 계산을 위해 “급여*12+커미션”이란 공식을 사용하겠습니다. (CH20_QUIZ_01.sql)

declare
    vemp emp%ROWTYPE;
BEGIN
    select * into vemp
    from emp
    where ename = 'SCOTT';
    vemp.comm := nvl(vemp.comm,0);
    DBMS_OUTPUT.PUT_LINE('연봉출력');
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
  DBMS_OUTPUT.PUT_LINE('사번 / 이름 / 연봉'); 
  DBMS_OUTPUT.PUT_LINE('----------------------');
  DBMS_OUTPUT.PUT_LINE(VEMP.EMPNO||'/'||VEMP.ENAME||'/'||ANNSAL); 
END;
/

SET SERVEROUTPUT ON
DECLARE
    VEMP EMP%ROWTYPE;
    VDNAME VARCHAR2(14);
BEGIN
    DBMS_OUTPUT.PUT_LINE('사번 / 이름 / 부서명');
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
    VDNAME := '개발부';
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


--다음은WHILE LOOP 문으로 별을 삼각형 구도로 출력하는 예입니다. 빈 공란을 채우시오.(CH20_QUIZ_04.sql)

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
DBMS_OUTPUT.PUT_LINE('부서번호 / 부서명 / 지역명'); 
DBMS_OUTPUT.PUT_LINE('----------------------------');
FOR VDEPT IN C1 LOOP
EXIT WHEN C1%NOTFOUND;
DBMS_OUTPUT.PUT_LINE(VDEPT.DEPTNO||
' '||VDEPT.DNAME||' '||VDEPT.LOC); 
END LOOP;
END;


--모든 사원명을 출력하고 그 옆에 사원명의 문자 수 만큼 * 출력

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




