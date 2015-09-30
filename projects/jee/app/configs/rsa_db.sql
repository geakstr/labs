-- rsa.db

create table insurances_coeffs (
  driver_license char(10) primary key not null,
  fio varchar(255) not null,
  coeff double not null 
);

insert into insurances_coeffs(driver_license, fio, coeff) values('1234567890', 'Джамалов Ахмед', 0.1);
insert into insurances_coeffs(driver_license, fio, coeff) values('2234567890', 'Зайченко Андрей', 0.3);