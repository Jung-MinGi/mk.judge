INSERT INTO problems (title, content, answer, category, level, removed)
VALUES ('test3', 'testContent3', 'testAnswer3', 'BRUTEFORCE', 'SILVER', false);
INSERT INTO problems (title, content, answer, category, level, removed)
VALUES ('test4', 'testContent4', 'testAnswer4', 'BRUTEFORCE', 'SILVER', false);
INSERT INTO problems (title, content, answer, category, level, removed)
VALUES ('test5', 'testContent5', 'testAnswer5', 'BRUTEFORCE', 'GOLD', false);
INSERT INTO problems (title, content, answer, category, level, removed)
VALUES ('test6', 'testContent6', 'testAnswer6', 'BRUTEFORCE', 'GOLD', false);
INSERT INTO problems (title, content, answer, category, level, removed)
VALUES ('test7', 'testContent7', 'testAnswer7', 'DP', 'SILVER', false);
INSERT INTO problems (title, content, answer, category, level, removed)
VALUES ('test8', 'testContent8', 'testAnswer8', 'DP', 'GOLD', false);
INSERT INTO problems (title, content, answer, category, level, removed)
VALUES ('test9', 'testContent9', 'testAnswer9', 'DP', 'SILVER', false);
INSERT INTO problems (title, content, answer, category, level, removed)
VALUES ('test10', 'testContent10', 'testAnswer10', 'GRAPH', 'GOLD', false);
INSERT INTO problems (title, content, answer, category, level, removed)
VALUES ('test11', 'testContent11', 'testAnswer11', 'GRAPH', 'SILVER', false);
INSERT INTO problems (title, content, answer, category, level, removed)
VALUES ('test12', 'testContent12', 'testAnswer12', 'GRAPH', 'SILVER', false);


insert into `User`(username,password,level,role) values('root','1234','SILVER','ROLE_ADMIN');
insert into `User`(username,password,level,role) values('admin','1234','SILVER','ROLE_ADMIN');
insert into `User`(username,password,level,role) values('aaa','1234','SILVER','ROLE_ADMIN');


insert into solved(username,id) values('root',1);
insert into solved(username,id) values('root',2);
insert into solved(username,id) values('root',4);
insert into solved(username,id) values('aaa',4);
insert into solved(username,id) values('aaa',6);
insert into solved(username,id) values('aaa',1);
insert into solved(username,id) values('admin',7);
insert into solved(username,id) values('admin',10);
insert into solved(username,id) values('admin',1);
insert into solved(username,id) values('admin',3);
