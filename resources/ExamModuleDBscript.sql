create schema ExamModuleDB;

use ExamModuleDB;

CREATE TABLE users (
  fullName varchar(40) NOT NULL,
  userName varchar(10) NOT NULL,
  passkey varchar(32) NOT NULL,
  access varchar(10) NOT NULL,
  PRIMARY KEY (userName)
);

insert into users values("Student4","1234567894","ee11cbb19052e40b07aac0ca060c23ee","4");
insert into users values("Student3","1234567893","ee11cbb19052e40b07aac0ca060c23ee","3");
insert into users values("Student2","1234567892","ee11cbb19052e40b07aac0ca060c23ee","2");
insert into users values("Admin1","1234567891","ee11cbb19052e40b07aac0ca060c23ee","1");

CREATE TABLE questions(
	question_order int NOT NULL AUTO_INCREMENT,
	question_id int NOT NULL,
	question_type varchar(1) NOT NULL,
	statement text NOT NULL,
	option1 text NOT NULL,
	option2 text NOT NULL,
	option3 text NOT NULL,
	option4 text NOT NULL,
	option5 text NOT NULL,
	answer text NOT NULL,
	question_image text,
	feedback_video_link text,
	feedback_image text,
	PRIMARY KEY (question_order,question_id,question_type)
);

select * from questions;
ALTER TABLE questions AUTO_INCREMENT=1;
delete from questions;
drop table questions cascade;
SET FOREIGN_KEY_CHECKS = 1; # 0 to ignore constraints and 1 to include 


CREATE TABLE Attempts(
	userName varchar(10) NOT NULL,
	question_order int NOT NULL,
	question_id int NOT NULL,
	question_type varchar(1) NOT NULL,
	start_time timestamp,
	attempt_time timestamp,
	selected_answer text NOT NULL,
	right_attempt bool,
	wrong_attempt bool,
	INDEX userName (userName),
    FOREIGN KEY (userName) 
        REFERENCES users(userName)
        ON DELETE CASCADE,
	FOREIGN KEY (question_order,question_id,question_type) 
        REFERENCES questions(question_order,question_id,question_type)
        ON DELETE CASCADE,
	PRIMARY KEY (userName, question_order,question_id,question_type)
);
drop table Attempts;
select * from Attempts;
delete from Attempts;

