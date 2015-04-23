create schema ExamModuleDB;

use ExamModuleDB;

CREATE TABLE users (
  fullName text NOT NULL,
  userName varchar(10) NOT NULL,
  passkey varchar(32) NOT NULL,
  access varchar(10) NOT NULL,
  account_not_blocked bool,
  PRIMARY KEY (userName)
);

insert into users values("Student4","nothing","ee11cbb19052e40b07aac0ca060c23ee","4",true);
insert into users values("Student3","image","ee11cbb19052e40b07aac0ca060c23ee","3",true);
insert into users values("Student2","video","ee11cbb19052e40b07aac0ca060c23ee","2",true);
insert into users values("Admin1","cvandesa","5f4dcc3b5aa765d61d8327deb882cf99","1",true);
select * from  users;
update users set account_not_blocked=true;
delete from users;
drop table users cascade;

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
ALTER TABLE questions AUTO_INCREMENT=1;
insert into questions(question_id,question_type,statement,option1,option2,option3,option4,option5,answer,question_image,feedback_video_link,feedback_image) values(1,"a","x^{2}+2xy+ y^{2} =25","x+y= \\pm 5","x-y=5","xy=5","x/y=5","x^{2}=5","x^{2}=5","DualGraph.jpg","https://miro.asu.edu/videos/online/MAT265-dheckman/MAT265-1-2_CompositeGraph_ex1_0232.mp4","DualGraph.jpg");
insert into questions(question_id,question_type,statement,option1,option2,option3,option4,option5,answer,question_image) values(1,"b","x^{2}+2xy+ y^{2} =25","x+y= \\pm 5","x-y=5","xy=5","x/y=5","x^{2}=5","x^{2}=5","DualGraph.jpg");
insert into questions(question_id,question_type,statement,option1,option2,option3,option4,option5,answer,question_image,feedback_video_link,feedback_image) values(2,"a","x^{2}+2xy+ y^{2} =25","x+y= \\pm 5","x-y=5","xy=5","x/y=5","x^{2}=5","x^{2}=5","DualGraph.jpg","https://miro.asu.edu/videos/online/MAT265-dheckman/MAT265-1-2_CompositeGraph_ex1_0232.mp4","DualGraph.jpg");
insert into questions(question_id,question_type,statement,option1,option2,option3,option4,option5,answer,question_image) values(2,"b","x^{2}+2xy+ y^{2} =25","x+y= \\pm 5","x-y=5","xy=5","x/y=5","x^{2}=5","x^{2}=5","DualGraph.jpg");
select * from questions;
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
select * from Attempts;
drop table Attempts;
delete from Attempts;