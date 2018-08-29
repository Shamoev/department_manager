CREATE schema depmanager1
;

USE depmanager1
;

create table departments
(
  dep_id int auto_increment
    primary key,
  depNumber int not null,
  depName varchar(255) not null,
  constraint departments_depName_uindex
  unique (depName)
)
  engine=InnoDB
;

create table employees
(
  empl_id int auto_increment
    primary key,
  surname varchar(255) not null,
  email varchar(255) not null,
  dateOfBirth date null,
  salary decimal(6,2) not null,
  constraint employees_email_uindex
  unique (email)
)
  engine=InnoDB
;

create table dep_empl
(
  dep_empl_id int auto_increment
    primary key,
  dep_id int null,
  empl_id int null,
  constraint de_dep_id
  foreign key (dep_id) references departments (dep_id)
    on update cascade on delete cascade,
  constraint de_empl_id
  foreign key (empl_id) references employees (empl_id)
    on update cascade on delete cascade
)
  engine=InnoDB
;

create index de_dep_id
  on dep_empl (dep_id)
;

create index de_empl_id
  on dep_empl (empl_id)
;

