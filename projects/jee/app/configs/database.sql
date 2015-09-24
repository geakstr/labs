create table users (
	username varchar(255) primary key not null,
	password varchar(44) not null
)

create table user_roles (
	username varchar(255) primary key not null,
	role varchar(32) not null,
	foreign key (username) references users(username)
)

--      a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=      this is hashed      1  
insert into users values ('admin1', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=')
insert into users values ('admin2', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=')
insert into users values ('employee1', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=')
insert into users values ('employee2', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=')


insert into user_roles values ('admin1', 'ADMIN')
insert into user_roles values ('admin2', 'ADMIN')
insert into user_roles values ('employee1', 'EMPLOYEE')
insert into user_roles values ('employee2', 'EMPLOYEE')

