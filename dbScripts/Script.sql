DROP SEQUENCE IF EXISTS user_sequence;

DROP SEQUENCE IF EXISTS training_sequence;

DROP TABLE IF EXISTS Training;

DROP TABLE IF EXISTS AppUser;

DROP TABLE IF EXISTS Sex;

CREATE TABLE Sex(
    sex_id INTEGER,
    sex    CHAR(1) NOT NULL
);

ALTER TABLE Sex
ADD CONSTRAINT sex_primary_key PRIMARY KEY (sex_id);

CREATE TABLE AppUser(
    user_id      INTEGER,
    passwordHash VARCHAR(20) NOT NULL,
    login        VARCHAR(20) NOT NULL,
    email        TEXT NOT NULL,
    user_age     INTEGER,
    sex_id       INTEGER
);

ALTER TABLE AppUser
ADD CONSTRAINT user_primary_key PRIMARY KEY (user_id);

ALTER TABLE AppUser
ADD CONSTRAINT user_sex_foreign_key FOREIGN KEY (sex_id) REFERENCES Sex(sex_id);

ALTER TABLE AppUser
ADD CONSTRAINT positive_user_age CHECK (user_age > 0);

CREATE SEQUENCE user_sequence OWNED BY AppUser.user_id;

CREATE TABLE Training(
    training_id         INTEGER,
    user_id             INTEGER,
    duration_in_minutes INTEGER NOT NULL,
    training_date       DATE NOT NULL
);

ALTER TABLE Training
ADD CONSTRAINT training_primary_key PRIMARY KEY (training_id);

ALTER TABLE Training
ADD CONSTRAINT training_user_foreign_key FOREIGN KEY (user_id) REFERENCES AppUser(user_id);

ALTER TABLE Training 
ADD CONSTRAINT positive_training_duration CHECK (duration_in_minutes > 0);

CREATE SEQUENCE training_sequence OWNED BY Training.training_id;