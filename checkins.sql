SET SCHEMA 'SMS_Checkins';

CREATE TABLE checkins
(
    checkin_id SERIAL,
    associate INTEGER NOT NULL,
    submitted TIMESTAMP WITH TIME ZONE NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT sms_checkins_PK PRIMARY KEY (checkin_id)
);