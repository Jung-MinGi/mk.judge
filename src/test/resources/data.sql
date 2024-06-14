insert into problems (title, content, answer, category, level,removed)
values ('test1', 'testContent1', 'testAnswer1', 'MATH', 'SILVER',false);
insert into problems ( title, content, answer, category, level,removed)
values ('test2', 'testContent2', 'testAnswer2', 'MATH', 'SILVER',false);
insert into problems ( title, content, answer, category, level,removed)
values ('test2', 'testContent2', 'testAnswer2', 'BRUTEFORCE', 'SILVER',false);


insert into `User`(username,password,level,role) values('root','1234','SILVER','ROLE_ADMIN');


insert into solved(username,id) values('root',1);
insert into solved(username,id) values('root',2);
--insert into solved(username,id) values('root',3);