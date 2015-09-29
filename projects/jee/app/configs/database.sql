-- insurance.db

create table users (
	username varchar(255) primary key not null,
	password varchar(44) not null,
	fio varchar(255) not null,
	role varchar(255) not null
)

create table drivers (
  license char(10) primary key not null,
  fio varchar(255) not null
)

create table cars (
  car_num char(6) not null primary key,
  car_model varchar(255) not null,
  car_power int not null,
  driver_license char(10) not null,
  foreign key (driver_license) references drivers(license)
)

create table insurances (
  id int auto_increment not null primary key,
  car_num char(6) not null,
  date_from date not null,
  date_to date not null,
  active boolean not null default true,
  cost double not null,
  foreign key (car_num) references cars(car_num)
)

create table insurance_to_driver (
  insurance_id int not null,
  driver_license char(10) not null,
  primary key (insurance_id, driver_license),
  foreign key (insurance_id) references insurances(id),
  foreign key (driver_license) references drivers(license),
)

--      a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=      this is hashed      1  
insert into users(username, password, fio, role) values('admin1', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=', 'Иванов Сергей', 'ADMIN')
insert into users(username, password, fio, role) values('admin2', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=', 'Петров Максим', 'ADMIN')
insert into users(username, password, fio, role) values('employee1', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=', 'Карпенко Андрей', 'EMPLOYEE')
insert into users(username, password, fio, role) values('employee2', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=', 'Сидоров Петя', 'EMPLOYEE')


insert into drivers(license, fio) values('1234567890', 'Джамалов Ахмед')
insert into drivers(license, fio) values('2234567890', 'Зайченко Андрей')


insert into cars(car_num, car_model, car_power, driver_license) values('а123вс', 'ВАЗ 2107', 70, '1234567890')
insert into cars(car_num, car_model, car_power, driver_license) values('в123вс', 'ВАЗ 2106', 60, '1234567890')
insert into cars(car_num, car_model, car_power, driver_license) values('а223вс', 'Kia Rio', 90, '2234567890')


insert into insurances(car_num, date_from, date_to, cost) values('а123вс', '2015-09-01', '2016-09-01', 8000)
insert into insurances(car_num, date_from, date_to, cost) values('в123вс', '2015-07-01', '2016-07-01', 10000)
insert into insurances(car_num, date_from, date_to, cost) values('а223вс', '2015-08-01', '2016-08-01', 12000)


insert into insurance_to_driver(insurance_id, driver_license) values(1, '1234567890')
insert into insurance_to_driver(insurance_id, driver_license) values(2, '1234567890')
insert into insurance_to_driver(insurance_id, driver_license) values(2, '2234567890')
insert into insurance_to_driver(insurance_id, driver_license) values(3, '2234567890')




-- rsa.db

create table insurances_coeffs (
  driver_license char(10) primary key not null,
  fio varchar(255) not null,
  coeff double not null 
)

insert into insurances_coeffs(driver_license, fio, coeff) values('1234567890', 'Джамалов Ахмед', 0.1)
insert into insurances_coeffs(driver_license, fio, coeff) values('2234567890', 'Зайченко Андрей', 0.3)