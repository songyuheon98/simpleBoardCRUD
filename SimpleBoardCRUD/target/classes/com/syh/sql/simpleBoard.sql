create table simpleBoard(
	id int not null auto_increment,
	title varchar(100) not null,
	content varchar(500) not null,
	writer varchar(50) not null,
	indate datetime default now(),
	count int default 0,
	primary key(id)
);


insert into simpleBoard(title,content,writer) values('title','content','admin');
insert into simpleBoard(title,content,writer) values('title','content','admin');
insert into simpleBoard(title,content,writer) values('title','content','admin');


select * from simpleBoard order by id desc;