SET SCHEMA 'user_service';

drop table users_cohorts;
drop table cohorts;
drop table status_history;
drop table sms_users;
drop table addresses;
drop table status;





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



