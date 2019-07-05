

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
		   ('Aaron', 'Gravelle', 'agrav12825@gmail.com', '1234567890', (SELECT address_id FROM addresses WHERE alias = 'USF'), 8),
		   ('Hermes', 'Lisoma', 'hermes.lisoma@example.com', '9035551234', (SELECT address_id FROM addresses WHERE alias = 'Reston'), 1),
		   ('David', 'Ubah', 'david.ubah@example.com', '9035554321', (SELECT address_id FROM addresses WHERE alias = 'Reston'), 1),
		   ('Robert', 'Simmons', 'robert.simmons@example.com', '9035556789', (SELECT address_id FROM addresses WHERE alias = 'Reston'), 1),
		   ('Elias', 'Friedman', 'elias.friedman@example.com', '9035559876', (SELECT address_id FROM addresses WHERE alias = 'Reston'), 1),
		   ('Michele', 'Dexter', 'michele.dexter@example.com', '9035551357', (SELECT address_id FROM addresses WHERE alias = 'Reston'), 1),
		   ('Tianzeng(Tony)', 'Liu', 'tianzeng.liu@example.com', '9035557531', (SELECT address_id FROM addresses WHERE alias = 'Reston'), 1),
		   ('Eva', 'Fann', 'eva.fann@example.com', '9035552468', (SELECT address_id FROM addresses WHERE alias = 'Reston'), 1),
		   ('James', 'C', 'james.c@example.com', '9035558642', (SELECT address_id FROM addresses WHERE alias = 'Reston'), 1),
		   ('Ernesto', 'Ballon', 'ernesto.ballon@example.com', '9035551010', (SELECT address_id FROM addresses WHERE alias = 'Reston'), 1),
		   ('Ryan', 'Walker', 'ryan.walker@example.com', '9035550101', (SELECT address_id FROM addresses WHERE alias = 'Reston'), 1),
		   ('Ralph', 'Metellus', 'ralph.metellus@example.com', '9035550110', (SELECT address_id FROM addresses WHERE alias = 'Reston'), 1),
		   ('Gerald', 'Horton', 'gerald.horton@example.com', '9095551234', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1),
		   ('Penelope', 'Winters', 'penelope.winters@example.com', '9095554321', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1),
		   ('Boris', 'Carlson', 'boris.carlson@example.com', '9095556789', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1),
		   ('Quyen', 'Lung', 'quyen.lung@example.com', '9095559876', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1),
		   ('Victor', 'Santos', 'victor.santos@example.com', '9095551357', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1),
		   ('Rajesh', 'Patel', 'rajesh.patel@example.com', '9095557531', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1),
		   ('Caleb', 'Widogast', 'caleb.widogast@example.com', '9095552468', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1),
		   ('Percival', 'Merriwether', 'percival.merriwether@example.com', '9095558642', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1),
		   ('Constance', 'Markham', 'constance.markham@example.com', '9095551010', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1),
		   ('Donald', 'Anders', 'donald.anders@example.com', '9095550101', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1),
		   ('Valorie', 'Wilson', 'valorie.wilson@example.com', '9095550110', (SELECT address_id FROM addresses WHERE alias = 'USF'), 1);



INSERT INTO cohorts
( cohort_name, cohort_description, cohort_token, trainer_id, start_date, end_date, address)
VALUES( 'Jaaamers', 'For jam enthusiasts', '20e48c2d-e9e6-4250-82bf-431927682324', 1, '2019-01-10 00:00:00.000', '2019-03-21 00:00:00.000', 2),
	  ( 'SurveyCrew', 'For people who love rocks', '20e48c2d-e9e6-4250-82bf-4333333333333', 1, '2019-01-10 00:00:00.000', '2019-03-21 00:00:00.000', 2),
	  ( 'Interviewers', 'For people who like to make others sweat', '20e48c2d-e9e6-4250-82bf-4555555555555', 1, '2019-01-10 00:00:00.000', '2019-03-21 00:00:00.000', 2),
	  ( 'Penguineers', 'For people who are in 1905JavaReact', '20e48c2d-e9e6-4250-82bf-43e07d3a70b1a', 2, '2019-05-06 00:00:00.000', '2019-07-12 00:00:00.000', 1),
	  ( 'Crocodocs', 'For people who are in a USF cohort', '20e48c2d-e9e6-4250-82bf-438580ab91dd0', 1, '2019-05-06 00:00:00.000', '2019-07-12 00:00:00.000', 1);


insert into users_cohorts
values (2,1),
	   (3,2),
	   (4,3),
	   (5,3),
	   (6,1),
	   (7,2),
	   (9,4),
	   (10,4),
	   (11,4),
	   (12,4),
	   (13,4),
	   (14,4),
	   (15,4),
	   (16,4),
	   (17,4),
	   (18,4),
	   (19,4),
	   (20,5),
	   (21,5),
	   (22,5),
	   (23,5),
	   (24,5),
	   (25,5),
	   (26,5),
	   (27,5),
	   (28,5),
	   (29,5),
	   (30,5);
	 
insert into status_history(users_id, status_id, address_id)
	values(1,15,2);

select * from status_history;

select * from cohorts;

select * from users_cohorts;

select * from addresses;

select * from sms_users;

select * from status;

