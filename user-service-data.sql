SET SCHEMA 'user_service';

INSERT INTO addresses (alias, street, zip, city, state)
	VALUES ('Reston Office', '11730 Plaza America Dr #205', 20190, 'Reston', 'VA'),
		   ('USF', 'Northwest Educational Complex', '33613', 'Tampa', 'FL');
		   
INSERT INTO sms_users (first_name, last_name, email, phone_number, address)
	VALUES ('Blake', 'Kruppa', 'blake.kruppa@revature.com', '9093804081', 2);

