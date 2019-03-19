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

select * from status_history;

select * from cohorts;

select * from users_cohorts;

select * from addresses;

select * from sms_users;

select * from status;
