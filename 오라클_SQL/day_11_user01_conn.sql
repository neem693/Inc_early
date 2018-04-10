create table T01(
C number(6)
);

create view t01_vw
as
select c from t01;


create index IDX_T01_C
on T01(C);


create sequence T01_SEQ
start with 1
increment by 1;


select *
from user02.book_tb;


create table book_tb(
book_code number(4) constraint book_pk primary key,
book_name varchar2(30 char) not null
);


insert into book_tb
values(1001,'ø¿∂Û≈¨');

