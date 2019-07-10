

DROP SCHEMA IF EXISTS user_service CASCADE;
CREATE SCHEMA user_service;
SET SCHEMA 'user_service';

CREATE TABLE addresses (
	address_id SERIAL PRIMARY KEY,
	alias TEXT,
	street TEXT NOT NULL,
	zip TEXT NOT NULL,
	city TEXT NOT NULL,
	state TEXT NOT NULL,
	country TEXT NOT NULL DEFAULT 'United States',
	is_training_location BOOLEAN NOT NULL DEFAULT false
);

create table status (
	status_id serial,
	general_status text not null,
	specific_status text not null,
	virtual boolean default false,
	constraint sms_status_pk primary key (status_id)
);

CREATE TABLE sms_users (
    sms_user_id SERIAL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone_number TEXT,
    training_address INTEGER NOT NULL REFERENCES addresses (address_id),
    personal_address INTEGER references addresses (address_id),
    user_status INTEGER not null references status (status_id),
    CONSTRAINT sms_users_PK PRIMARY KEY (sms_user_id)
);

CREATE TABLE cohorts (
    cohort_id SERIAL,
    cohort_name TEXT NOT NULL UNIQUE,
    cohort_description TEXT,
    cohort_token TEXT,
    trainer_id INTEGER NOT NULL,
    start_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	  address INTEGER  NOT NULL REFERENCES addresses (address_id),
    CONSTRAINT sms_cohorts_PK PRIMARY KEY (cohort_id),
    CONSTRAINT sms_cohorts_FK_trainer FOREIGN KEY (trainer_id)
    REFERENCES sms_users (sms_user_id) ON DELETE CASCADE
);

CREATE TABLE users_cohorts (
    sms_user_id INTEGER NOT NULL,
    cohort_id INTEGER NOT NULL,
    CONSTRAINT sms_users_cohorts_PK PRIMARY KEY (sms_user_id, cohort_id),
    CONSTRAINT sms_users_cohorts_FK_user FOREIGN KEY (sms_user_id)
    REFERENCES sms_users (sms_user_id) ON DELETE CASCADE,
    CONSTRAINT sms_users_cohorts_FK_cohort FOREIGN KEY (cohort_id)
    REFERENCES cohorts (cohort_id) ON DELETE CASCADE
);

create table status_history (
	status_history_id serial,
	status_start timestamp NOT NULL DEFAULT now()::timestamp without time zone,
	users_id INTEGER not null,
	status_id integer not null,
	address_id integer not null,
	CONSTRAINT status_history_PK PRIMARY KEY (status_history_id),
	CONSTRAINT status_history_FK_user FOREIGN KEY (users_id)
    REFERENCES sms_users (sms_user_id),
    CONSTRAINT status_history_FK_status FOREIGN KEY (status_id)
    REFERENCES status (status_id),
    CONSTRAINT status_history_FK_address FOREIGN KEY (address_id)
    REFERENCES addresses (address_id)
);

create table managers (
	id serial,
	email text unique,
	address integer references addresses (address_id)
);

SET SCHEMA 'user_service';

insert into status(general_status, specific_status, virtual)
	values('Training', 'Dropped', false),
		  ('Training', 'Training', false),
		  ('Training', 'Complete', false),
		  ('Staging', 'Staging', false),
		  ('Staging', 'Bench', false),
		  ('Staging', 'Waiting for Paperwork', false),
		  ('Staging', 'Confirmed', false),
		  ('Staging', 'Project Started', false),
		  ('Staging', 'Paused', false),
		  ('Staging', 'Panel Pending', false),
		  ('Staging', 'Staging', true),
		  ('Staging', 'Bench', true),
		  ('Staging', 'Waiting for Paperwork', true),
		  ('Staging', 'Confirmed', true),
		  ('Staging', 'Project Started', true),
		  ('Staging', 'Paused', true),
		  ('Staging', 'Panel Pending', true);



		 
INSERT INTO addresses (alias, street, zip, city, state, is_training_location)
	VALUES ('Reston', '11730 Plaza America Dr #205', 20190, 'Reston', 'VA', TRUE),
		   ('USF', 'Northwest Educational Complex', '33613', 'Tampa', 'FL', TRUE);
		   
INSERT INTO sms_users (first_name, last_name, email, phone_number, training_address, user_status)
	VALUES ('Blake', 'Kruppa', 'blake.kruppa@revature.com', '9093804081', (SELECT address_id FROM addresses WHERE alias = 'USF'), 8),
		   ('Alec', 'Batson', 'abatson94@gmail.com', '9093804081', (SELECT address_id FROM addresses WHERE alias = 'USF'), 8),
		   ('Lori', 'Oliver', 'loricodes@gmail.com', '9093804081', (SELECT address_id FROM addresses WHERE alias = 'USF'), 8),
		   ('Kenneth', 'Currie', 'kenneth.james.currie@gmail.com', '9093804081', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1),
		   ('Dom', 'Felix', 'dfeli014@fiu.edu', '9093804081', (SELECT address_id FROM addresses WHERE alias = 'USF'), 10),
		   ('Mohamed', 'Omar', 'mohamedwomar21@gmail.com', '9093804081', (SELECT address_id FROM addresses WHERE alias = 'USF'),7),
		   ('John', 'Goncalves', 'goncalvesjohnp@gmail.com', '9093804081', (SELECT address_id FROM addresses WHERE alias = 'USF'), 8),
		   ('Aaron', 'Gravelle', 'agrav12825@gmail.com', '1234567890', (SELECT address_id FROM addresses WHERE alias = 'USF'), 8);


INSERT INTO cohorts
( cohort_name, cohort_description, cohort_token, trainer_id, start_date, end_date, address)
VALUES( 'Jaaamers', 'For jam enthusiasts', '20e48c2d-e9e6-4250-82bf-431927682324', 1, '2019-01-10 00:00:00.000', '2019-03-21 00:00:00.000', 2),
	  ( 'SurveyCrew', 'For people who love rocks', '20e48c2d-e9e6-4250-82bf-4333333333333', 1, '2019-01-10 00:00:00.000', '2019-03-21 00:00:00.000', 2),
	  ( 'Interviewers', 'For people who like to make others sweat', '20e48c2d-e9e6-4250-82bf-4555555555555', 1, '2019-01-10 00:00:00.000', '2019-03-21 00:00:00.000', 2);


insert into users_cohorts
values (2,1),
	   (3,2),
	   (4,3),
	   (5,3),
	   (6,1),
	   (7,2);
	 
	 
insert into status_history(users_id, status_id, address_id)
	values(1,15,2);
	
insert into managers (email, address) values ('blake.kruppa@revature.com', (select address_id from addresses where alias = 'Reston'));

select * from status_history;

select * from cohorts;

select * from users_cohorts;

select * from addresses;

select * from sms_users;

select * from status;

select * from user_service.status_history as sh left join user_service.sms_users as su on sh.users_id = su.sms_user_id left join user_service.status as s on sh.status_id = s.status_id where status_start between '06/25/2019' and '07/02/2019'

select * from user_service.sms_users left join user_service.status_history on users_id = sms_user_id where status_start between '06/25/2019' and '07/02/2019'
																																
select * from user_service.sms_users left join user_service.status_history on users_id = sms_user_id where datediff(day, status_start, now()) = 7

select count(*) as total from (select * from user_service.sms_users left join user_service.status_history on users_id = sms_user_id where status_start between now() - INTERVAL '168 HOURS' and now())as total

select * from user_service.sms_users left join user_service.status_history on users_id = sms_user_id where status_start between now() - INTERVAL '168 HOURS' and now()
