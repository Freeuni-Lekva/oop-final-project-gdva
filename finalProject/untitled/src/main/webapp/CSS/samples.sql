use finalproject;

drop table if exists admins;
drop table if exists posts;
drop table if exists messages;
drop table if exists debug;
drop table if exists friends;
drop table if exists questions;
drop table if exists quiz_history;
drop table if exists quizzes;
drop table if exists sent_requests;
drop table if exists accounts;

create table accounts (
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

create table posts(
                      id int primary key,
                      title varchar(255),
                      plot varchar(1000),
                      img varchar(255),
                      upload_date Date,
                      author_id int,
                      foreign key (author_id) references accounts(id)
);

create table debug(
    txt varchar(255)
);

create table messages(
                         id int auto_increment primary key,
                         from_id int,
                         to_id int,
                         send_date datetime,
                         txt varchar(1000),
                         message_type varchar(1000),
                         foreign key(from_id) references accounts(id),
                         foreign key(to_id) references accounts(id)
);

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
                        answer_type varchar(55),
                        creator_id int,
                        quiz_description varchar(1000),
                        create_date datetime,
                        foreign key (creator_id) references accounts(id)
);

create table questions(
                          question_id  int auto_increment primary key,
                          question_type varchar(55),
                          question_text varchar(255),
                          question_answer varchar(255),
                          question_image varchar(255),
                          question_choices_number int,
                          question_grade double,
                          quiz_id int,
                          foreign key(quiz_id) references quizzes(id)
);

create table quiz_history(
                             quiz_id int,
                             account_id int,
                             score double,
                             time int,
                             start_date datetime,
                             foreign key(quiz_id) references quizzes(id),
                             foreign key(account_id) references accounts(id)
);

insert into Accounts(id,firstname,surname,username,pass,age,img) values
                                                                     (1,'baro','lasha','barolasha','barolasha',20,'baro.jpg'),
                                                                     (2,'baro','lasha','barolasha','barolasha',20,'baro.jpg'),
                                                                     (3,'baro','lasha','barolasha','barolasha',20,'baro.jpg'),
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

insert into admins(id) values (1);

insert into posts(id,title,plot,img,upload_date,author_id) values
                                                               (2,'kachoki lasa','kachoki lasa dakachokda kachaobit sakachokeshi','lasa.jpg',sysdate(),1),
                                                               (3,'koba koba','gamarjobat me var koba da ravi ra ratomac ara','koba.jpg',sysdate(),1),
                                                               (1,'fxalla fxala','fxalas sicxe aq','fxala.jpg',sysdate(),1);