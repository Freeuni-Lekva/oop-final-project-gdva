use finalProject;


drop table if exists Accounts;
drop table if exists admins;
drop table if exists posts;
drop table if exists messages;
drop table if exists debug;

create table Accounts (
	id int primary key,
    firstname VARCHAR(255),
    surname VARCHAR(255),
    username VARCHAR(255),
    pass VARCHAR(255),
    age int,
    img varchar(255)
);

create table admins (
    id int,
    foreign key (id) references accounts(id)
);

insert into Accounts(id,firstname,surname,username,pass,age,img) values
(4,'baro','lasha','barolasha','barolasha',20,'baro.jpg'),
(5,'beka','beka','bekabeka','bekabeka',20,'beka.jpg'),
(6,'beso','beso','besobeso','besobeso',20,'beso.jpg'),
(7,'cima','cima','cimacima','cimacima',20,'cima.jpg'),
(8,'deme','deme','demedeme','demedeme',20,'deme.jpg'),
(9,'keke','keke','kekekeke','kekekeke',20,'keke.jpg'),
(10,'kizo','kizo','kizokizo','kizokizo',20,'kizo.jpg'),
(11,'lukicha','lukicha','lukichalukicha','lukichalukicha',20,'lukicha.jpg'),
(12,'misho','misho','mishomisho','mishomisho',20,'misho.jpg'),
(13,'niko','niko','nikoniko','nikoniko',20,'niko.jpg'),
(14,'nino','nino','ninonino','ninonino',20,'nino.jpg');


delete from Accounts where id  = 4;

select * from accounts;

insert into admins(id) value (2);
select * from admins;
create table posts(
	id int primary key,
	title varchar(255),
    plot varchar(1000),
    img varchar(255),
    upload_date Date,
    author_id int,
    foreign key (author_id) references accounts(id)
);
select * from accounts;
insert into posts(id,title,plot,img,upload_date,author_id) values
(2,'kachoki lasa','kachoki lasa dakachokda kachaobit sakachokeshi','lasa.jpg',sysdate(),0),
(3,'koba koba','gamarjobat me var koba da ravi ra ratomac ara','koba.jpg',sysdate(),0),
(4,'fxalla fxala','fxalas sicxe aq','fxala.jpg',sysdate(),0);

delete from posts where id = 1;
select * from posts;

select * from messages where from_id = 0 or to_id = 0 order by send_date, id desc;
select * from accounts where id = (select max(id) from accounts);

drop table if exists messages;
create table debug(
	txt varchar(255)
);
select * from debug;
select * from messages;
create table messages(
	id int auto_increment primary key,
    from_id int,
    to_id int,
    send_date datetime,
    txt varchar(1000),
    foreign key(from_id) references accounts(id),
    foreign key(to_id) references accounts(id)
);

INSERT INTO messages(from_id, to_id, send_date, txt)
SELECT from_id, to_id, SYSDATE(), CONCAT('Random message ', n)
FROM (
    SELECT from_id, IF(from_id = 0, FLOOR(RAND() * 14) + 1, 0) AS to_id, n
    FROM (
        SELECT IF(RAND() < 0.5, 0, FLOOR(RAND() * 14) + 1) AS from_id,
               @rownum := @rownum + 1 AS n
        FROM information_schema.tables, (SELECT @rownum := 0) r
        LIMIT 1000
    ) subquery1
) subquery2
WHERE from_id != 2 AND to_id != 2;




select * from messages;

select * from messages where send_date = (select max(send_date) from messages where from_id = 1 or to_id = 1) order by id desc;

insert into debug(txt) value ('nika');
select * from messages where from_id = 0 or to_id = 0 order by send_date;
select from_id, to_id from messages where id = (select max(id) from messages where from_id = 0 or to_id = 0);
select * from debug;
select * from messages;

create table sent_requests(
                              sender_id int,
                              receiver_id int,
                              foreign key (sender_id) references accounts(id),
                              foreign key (sender_id) references accounts(id)
);

create table friends(
                        first_friend_id int,
                        second_friend_id int,
                        foreign key (first_friend_id) references accounts(id),
                        foreign key (second_friend_id) references accounts(id)
);
create table quizzes(
id int auto_increment primary key,
title varchar(55),
question_order varchar(55),
question_alignment varchar(55),
answer_type varchar(55)
);

create table questions(
	question_id  int auto_increment primary key,
	question_type varchar(55),
    question_text varchar(255),
    question_answer varchar(255),
    question_image varchar(255),
    question_choices_number int,
    question_grade double
);
select * from quizzes;
insert into questions(question_type,question_text,question_answer,question_image,question_choices_number,question_grade) value('QuestionRespone','gg','gia','',0,1.0);
select max(question_id) as "id" from questions;
select * from questions;
delete from questions where question_id = 1;
drop table question
drop table quiz_questions
create table quiz_questions(
	quiz_id int,
    question_id int,
    foreign key(quiz_id) references quizzes(id),
    foreign key(question_id) references questions(question_id)
);
