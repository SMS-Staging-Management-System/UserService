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


select * from status;
		 
INSERT INTO addresses (alias, street, zip, city, state, is_training_location)
	VALUES ('Reston', '11730 Plaza America Dr #205', 20190, 'Reston', 'VA', TRUE),
		   ('USF', 'Northwest Educational Complex', '33613', 'Tampa', 'FL', TRUE);
		   
INSERT INTO sms_users (first_name, last_name, email, phone_number, training_address, personal_address, user_status)
	VALUES ('Blake', 'Kruppa', 'blake.kruppa@revature.com', '9093804081', (SELECT address_id FROM addresses WHERE alias = 'USF'),(SELECT address_id FROM addresses WHERE alias = 'USF'), 8);


INSERT INTO sms_users (first_name, last_name, email, phone_number, training_address, personal_address, user_status)
	VALUES ('Alec', 'Batson', 'abatson94@gmail.com', '9093804081', (SELECT address_id FROM addresses WHERE alias = 'USF'),(SELECT address_id FROM addresses WHERE alias = 'USF'), 8);



insert into status_history(users_id, status_id, address_id)
	values(1,15,2);

select * from status_history;

select * from cohorts;

select * from users_cohorts;
