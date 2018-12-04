SET SCHEMA 'SMS_Users';

CREATE TABLE roles
(
    role_id SERIAL,
    sms_role TEXT NOT NULL UNIQUE,
    CONSTRAINT sms_roles_PK PRIMARY KEY (role_id)
);

CREATE TABLE sms_users
(
    sms_user_id SERIAL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    username TEXT NOT NULL UNIQUE,
    user_role INTEGER NOT NULL,
    CONSTRAINT sms_users_PK PRIMARY KEY (sms_user_id),
    CONSTRAINT sms_users_FK_role FOREIGN KEY (user_role)
    REFERENCES roles (role_id) ON DELETE CASCADE
);

CREATE TABLE cohorts 
(
    cohort_id SERIAL,
    cohort_name TEXT NOT NULL UNIQUE,
    cohort_description TEXT NOT NULL,
    cohort_token TEXT NOT NULL,
    CONSTRAINT sms_cohorts_PK PRIMARY KEY (cohort_id)
);

CREATE TABLE users_cohorts
(
    sms_user INTEGER NOT NULL,
    cohort INTEGER NOT NULL,
    CONSTRAINT sms_users_cohorts_PK PRIMARY KEY (sms_user, cohort),
    CONSTRAINT sms_users_cohorts_FK_user FOREIGN KEY (sms_user)
    REFERENCES sms_users (sms_user_id) ON DELETE CASCADE,
    CONSTRAINT sms_users_cohorts_FK_cohort FOREIGN KEY (cohort)
    REFERENCES cohorts (cohort_id) ON DELETE CASCADE
);