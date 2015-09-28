create table users (
	username varchar(255) primary key not null,
	password varchar(44) not null,
	fio varchar(255) not null,
	role varchar(255) not null
)

--      a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=      this is hashed      1  
insert into users values ('admin1', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=', 'Иванов Сергей', 'ADMIN')
insert into users values ('admin2', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=', 'Петров Максим', 'ADMIN')
insert into users values ('employee1', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=', 'Карпенко Андрей', 'EMPLOYEE')
insert into users values ('employee2', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=', 'Сидоров Петя', 'EMPLOYEE')


