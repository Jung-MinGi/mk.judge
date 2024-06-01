create table `User`(
 username varchar(10) primary key,
 password varchar(100),
 level ENUM('GOLD', 'SILVER', 'BRONZE') NOT NULL
);

create table problems(
id int primary key auto_increment,
title varchar(20),
content varchar(255),
answer varchar(20),
category enum('MATH','BRUTEFORCE'),
grade enum('ONE','TWO','THREE')
);