CREATE TABLE dept01
    AS
        SELECT
            *
        FROM
            dept;

SELECT
    *
FROM
    dept01;

TRUNCATE TABLE dept01;

INSERT INTO dept01 (
    deptno,
    dname,
    loc
) VALUES (
    10,
    'ACCOUNTING',
    'NEW YORK'
);

INSERT INTO dept01 (
    deptno,
    dname,
    loc
) VALUES (
    15,
    '개발',
    '서울'
);

INSERT INTO dept01 VALUES (
    20,
    'RESEARCH',
    'DALLAS'
);

INSERT INTO dept01 VALUES (
    25,
    '디자인',
    '부산'
);

--서브 쿼리문을 이용하여 다음과 같은 구조로 SAM01 테이블을 생성하시오. 존재할 경우 DROP TABLE로 삭제 후 생성하시오.

CREATE TABLE sam01 (
    empno   NUMBER(4),
    ename   VARCHAR(10),
    job     VARCHAR(9),
    sal     NUMBER(7,2)
);

TRUNCATE TABLE sam01;

DROP TABLE sam01;

CREATE TABLE sam01
    AS
        SELECT
            empno,
            ename,
            job,
            sal
        FROM
            emp
        WHERE
            1 = 0;

DESC sam01;

--SAM01 테이블에 다음과 같은 데이터를 추가하시오.

INSERT INTO sam01 (
    empno,
    ename,
    job,
    sal
) VALUES (
    1000,
    'APPLE',
    'POLICE',
    10000
);

INSERT INTO sam01 (
    empno,
    ename,
    job,
    sal
) VALUES (
    1010,
    'BANANA',
    'NURSE',
    15000
);

INSERT INTO sam01 (
    empno,
    ename,
    job,
    sal
) VALUES (
    1020,
    'ORANGE',
    'DOCTOR',
    25000
);

SELECT
    *
FROM
    sam01;

INSERT INTO dept01 (
    deptno,
    dname
) VALUES (
    30,
    'SALES'
);

SELECT
    *
FROM
    dept01;

INSERT INTO dept01 (
    deptno,
    dname,
    loc
) VALUES (
    NULL,
    '',
    'NEW YORK'
);

INSERT INTO sam01 (
    empno,
    ename,
    job,
    sal
) VALUES (
    1030,
    'VERY',
    NULL,
    25000
);

INSERT INTO sam01 (
    empno,
    ename,
    job,
    sal
) VALUES (
    1030,
    'CAT',
    NULL,
    2000
);

SELECT
    *
FROM
    sam01;

INSERT INTO dept01
    SELECT
        *
    FROM
        dept;

INSERT ALL INTO dept01 VALUES (
    26,
    '디자인1',
    '부산'
) INTO dept01 VALUES (
    27,
    '디자인2',
    '부산'
) SELECT
    *
  FROM
    dual;

DROP TABLE emp01;

CREATE TABLE emp01
    AS
        SELECT
            *
        FROM
            emp;

--모든 사원의 부서번호를 30번으로 수정합시다.

UPDATE emp01
    SET
        deptno = 30;
--이번엔 모든 사원의 급여를 10% 인상시키는 UPDATE 문을 보겠습니다.

UPDATE emp01
    SET
        sal = sal * 1.1;

--모든 사원의 입사일을 오늘로 수정하려면 다음과 같이 합니다.

UPDATE emp01
    SET
        hiredate = SYSDATE;

SELECT
    *
FROM
    emp01;

--부서번호가 10번인 사원의 부서번호를 30번으로 수정합시다.

TRUNCATE TABLE emp01;

INSERT INTO emp01
    SELECT
        *
    FROM
        emp;

UPDATE emp01
    SET
        deptno = 30
WHERE
    deptno = 10;
--급여가 3000 이상인 사원만 급여를 10% 인상합시다. 

UPDATE emp01
    SET
        sal = sal * 1.1
WHERE
    sal > 3000;

--1987년에 입사한 사원의 입사일이 오늘로 수정합시다. 사원의 입사일을 오늘로 수정한 후에 테이블 내용을 살펴봅시다.(문제수정하겠다.)
--1982년에 입사한 사원의 입사일이 오늘로 수정합시다. 사원의 입사일을 오늘로 수정한 후에 테이블 내용을 살펴봅시다.

UPDATE emp01
    SET
        hiredate = SYSDATE
WHERE
    substr(hiredate,1,2) = '82';

SELECT
    substr(hiredate,1,2)
FROM
    emp01;

--SAM01 테이블에 저장된 사원 중 급여가 10000(문제수정= 5000) 이상인 사원들의 급여만 5000(문제수정 500)원씩 삭감하시오.

SELECT
    *
FROM
    emp01;

UPDATE emp01
    SET
        sal = sal - 500
WHERE
    sal >= 5000;

--SCOTT 사원의 부서번호는 20번으로, 직급은 MANAGER로 한꺼번에 수정하도록 합시다.

UPDATE emp01
    SET
        deptno = 20,
        job = 'MANAGER'
WHERE
    ename = 'SCOTT';

--SCOTT 사원의 입사일자는 오늘로, 급여를 50 으로 커미션을 4000 으로 수정합시다.

UPDATE emp01
    SET
        hiredate = SYSDATE,
        sal = 50,
        comm = 4000
WHERE
    ename = 'SCOTT';
--20번 부서의 지역명을 40번 부서의 지역명으로 변경하기 위해서 서브 쿼리문을 사용해 봅시다.

UPDATE dept01
    SET
        loc = (
            SELECT
                loc
            FROM
                dept
            WHERE
                deptno = 40
        )
WHERE
    deptno = 20;

SELECT
    *
FROM
    dept01;

SELECT
    *
FROM
    dept;

--부서 번호가 20번인 부서의 이름과 지역은 RESEARCH와 DALLAS입니다. 다음은 부서번호가 20인 부서의 부서명과 지역명을 부서 번호가 40번인 부서와 동일하게 변경하기 위한 UPDATE 명령문입니다.

DELETE FROM dept01 WHERE
    deptno = 30;

CREATE TABLE dept (
    deptno   NUMBER(2),--부서번호 
    dname    VARCHAR2(14),--부서명
    loc      VARCHAR2(13)
);--부서위치;

INSERT INTO dept VALUES (
    10,
    'ACCOUNTING',
    'NEW YORK'
);--경영재무회계부;

INSERT INTO dept VALUES (
    20,
    'RESEARCH',
    'DALLAS'
);--연구부;

INSERT INTO dept VALUES (
    30,
    'SALES',
    'CHICAGO'
);--영업부;

INSERT INTO dept VALUES (
    40,
    'OPERATIONS',
    'BOSTON'
);--총무부;

SELECT
    *
FROM
    dept;

DELETE FROM emp01
WHERE
    deptno = (
        SELECT
            deptno
        FROM
            dept
        WHERE
            dname = 'SALES'
    );

--부서 번호가 20번인 부서의 이름과 지역은 RESEARCH와 DALLAS입니다. 다음은 부서번호가 20인 부서의 부서명과 
--지역명을 부서 번호가 40번인 부서와 동일하게 변경하기 위한 UPDATE 명령문입니다.

UPDATE dept01
    SET
        ( dname,
        loc ) = (
            SELECT
                dname,
                loc
            FROM
                dept
            WHERE
                deptno = 40
        )
WHERE
    deptno = 20;


--분류코드 테이블 만들기

CREATE TABLE 분류코드 (
    분류코드값   NUMBER(3),
    코드명     VARCHAR(20),
    사용여부    VARCHAR(1)
);

--도서 테이블 만들기

CREATE TABLE 도서 (
    도서코드    NUMBER(3),
    도서명     VARCHAR(20),
    기본설명    VARCHAR(4000),
    상세설명    CLOB,
    등록일     DATE,
    분류코드값   NUMBER(3)
);

--관리용(예로 코드성) 데이터를 분류코드 테이블에 추가

INSERT all
INTO 분류코드 VALUES (100,  '오라클',      'Y')
INTO 분류코드 VALUES (200,  '자바',       'Y')
INTO 분류코드 VALUES (300,  '안드로이드',    'Y')
select * from dual;

delete from 분류코드;
select * from 분류코드;


INSERT ;


--DB관리(CRUD)
--SQL로 관리 (응용)프로그램 개발
--도서코드(분류코드에 일련번호)

INSERT ALL  INTO 도서 VALUES (101,    '오라클1',     '기본',   '상세',   '2017-06-24',   100) 
            INTO 도서 VALUES (102,    '오라클2',     '기본',   '상세',   '2017-06-24',   100) 
            INTO 도서 VALUES (201,    '자바1',       '기본',    '상세',   '2017-06-25',  200) 
            INTO 도서 VALUES (301,    '안드로이드1',  '기본',    '상세',   '2017-06-24',  300)
SELECT *FROM dual;    
--'오라클'의 모든 도서의 도서코드, 도서명, 기본설명 출력

SELECT
    도서코드,
    도서명,
    기본설명
FROM
    도서
WHERE
    substr(도서코드,1,1) = '1';
--where 도서코드 between 100 and 199;
--where 분류코드값 = 100;
--where 도서코드 between 100 and 199;
--where 도서코드 like '1__';
--where substr(도서코드,1,1)= '1';

select *
from 도서;

--도서별로 도서 개수를 출력

SELECT
    c.코드명,
    COUNT(*)
FROM
    도서 b
    INNER JOIN 분류코드 c ON b.분류코드값 = c.분류코드값
GROUP BY
    c.코드명;
    
    SELECT
    *
FROM
    도서 b
    INNER JOIN 분류코드 c ON b.분류코드값 = c.분류코드값;
    
    --총 도서 갯수를 출력하라(단, rollup함수 사용할 것)
    select decode(grouping(분류코드값),1,'총 갯수',0,분류코드값) as "분류코드", count(*)
    from 도서
    group by rollup(분류코드값);

select grouping(분류코드값),count(*)
from 도서
group by rollup(분류코드값);

select nvl2(분류코드값,to_char(분류코드값),'총 갯수') as "분류코드",count(*)
from 도서
group by rollup(분류코드값);

create table 이그지스트_그룹(
GR number(1),
EL CHAR(1));

insert all
into 이그지스트_그룹("GR","EL") VALUES (1,'A')
into 이그지스트_그룹("GR","EL") VALUES (1,'B')
into 이그지스트_그룹("GR","EL") VALUES (1,'C')
into 이그지스트_그룹("GR","EL") VALUES (2,'A')
into 이그지스트_그룹("GR","EL") VALUES (3,'B')
into 이그지스트_그룹("GR","EL") VALUES (4,'A')
into 이그지스트_그룹("GR","EL") VALUES (4,'B')
into 이그지스트_그룹("GR","EL") VALUES (5,'A')
into 이그지스트_그룹("GR","EL") VALUES (5,'C')
select * from dual;

delete from 이그지스트_그룹;

drop table 이그지스트_그룹;

select *
from 이그지스트_그룹;

select *
from 이그지스트_그룹 e inner join 이그지스트_그룹 f
on e.gr = f.gr;

select e.gr,e.el
from 이그지스트_그룹 e
where exists( select 1
              from 이그지스트_그룹
              where gr = e.gr and
              el=e.el and
                    (el= 'A' OR el= 'B'));
select empno,ename,sal,deptno
from emp e
where exists (select max(sal)
from emp
group by deptno
having e.sal = max(sal));

--도서코드가 301인 도서의 기본설명을 '저자 : 홍길동'으로 수정


