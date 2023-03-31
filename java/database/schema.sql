BEGIN TRANSACTION;


DROP TABLE IF EXISTS volunteers;
DROP TABLE IF EXISTS application_status;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS pets;

CREATE TABLE application_status (
	application_status_id serial NOT NULL,
	application_status_desc varchar(10) NOT NULL,
	CONSTRAINT PK_application_status PRIMARY KEY (application_status_id)
);

/*
	CREATE SEQUENCE seq_user_id
  	INCREMENT BY 1
  	START WITH 1001
  	NO MAXVALUE;
*/


CREATE TABLE users (
	/*Possibly change user_id from serial to:
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	to allow the two tables to match up*/
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) default ('$2a$10$FgQ0ujuzF2bFWdGC6ngZnejKbtYLk4c.KoCN2mfPIDn5kiCWarley'),
	role varchar(50) NOT NULL default ('ROLE_USER'),
	logins int default 0,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE pets (
	id SERIAL,
	gender varchar(6),
	pet_name varchar(50) NOT NULL,
    animal_type varchar(50) NOT NULL,
	color varchar(50) NOT NULL,
	age  decimal(3,1),
	image_link varchar(250),
	is_available boolean default(true),
	spayed_neutered boolean default(true),
	tagline varchar(500),
	CONSTRAINT PK_pets PRIMARY KEY (id)
);
CREATE TABLE volunteers (
    volunteer_id SERIAL,
	username varchar(100) unique NOT NULL,
	name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    phone bigint NOT NULL,
    application_status_id int NOT NULL DEFAULT(1),
    CONSTRAINT PK_volunteer PRIMARY KEY (volunteer_id),
	CONSTRAINT FK_volunteer_application_status FOREIGN KEY (application_status_id) REFERENCES application_status(application_status_id)
);

INSERT INTO application_status (application_status_desc) VALUES ('Pending');
INSERT INTO application_status (application_status_desc) VALUES ('Approved');
INSERT INTO application_status (application_status_desc) VALUES ('Rejected');


COMMIT TRANSACTION;
