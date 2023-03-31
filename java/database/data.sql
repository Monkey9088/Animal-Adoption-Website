BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$10$FgQ0ujuzF2bFWdGC6ngZnejKbtYLk4c.KoCN2mfPIDn5kiCWarley','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$10$FgQ0ujuzF2bFWdGC6ngZnejKbtYLk4c.KoCN2mfPIDn5kiCWarley','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) 
		VALUES ('Joseph','$2a$10$FgQ0ujuzF2bFWdGC6ngZnejKbtYLk4c.KoCN2mfPIDn5kiCWarley','ROLE_ADMIN'),
				('Abigail','$2a$10$FgQ0ujuzF2bFWdGC6ngZnejKbtYLk4c.KoCN2mfPIDn5kiCWarley','ROLE_ADMIN'),
				('Adam','$2a$10$FgQ0ujuzF2bFWdGC6ngZnejKbtYLk4c.KoCN2mfPIDn5kiCWarley','ROLE_ADMIN'),
				('Ruina','$2a$10$FgQ0ujuzF2bFWdGC6ngZnejKbtYLk4c.KoCN2mfPIDn5kiCWarley','ROLE_ADMIN');


INSERT INTO pets (gender, pet_name, animal_type, color, age, image_link, tagline)
        VALUES  ('Male', 'Alfred', 'cat', 'brown', 2, '/img/alfred.jpg', 'I am in need of a human. Pick me!'),
                ('Female','Beethoven','cat', 'striped brown', 0.3, '/img/beethoven.jpg', 'I am so cute! I love to play and cuddle!'),
                ('Female', 'Fifi', 'dog', 'white and brown', 6, '/img/fifi.jpg', 'A loyal dog who just wants a human friend to love!'),
                ('Male', 'Garfunkel', 'cat', 'caramel', 7, '/img/garfunkel.jpg', 'Come play with me for a purrfect time!'),
                ('Female', 'Kutie', 'cat', 'cream', 2, '/img/kutie.jpg', 'Just give me a bowl of the best and I am yours for the rest of time!'),
                ('Female', 'Stinky', 'dog', 'midnight black', 3, '/img/stinky.jpg', 'I hate to bathe but I love to play!'),
                ('Female', 'Zippers', 'bunny', 'grey with white face', 2, '/img/zippers.jpg', 'Nom Nom Nom I love to eat and look so cute!'),
                ('Male', 'Dingus', 'cat', 'black', 1, '/img/dingus.png', 'I scream with joy to play with toys...and I am super cute!');


INSERT INTO volunteers (username, name, email, phone, application_status_id)
        VALUES ('Joseph123', 'Joseph Stephens', 'joseph123@gmail.com', 5551111, 2),
                ('Abigail456', 'Abigail Newman', 'abigail456@gmail.com', 5552222, 2),
                ('Adam', 'Adam Shaw', 'adam789@gmail.com', 5553333, 2),
				('Ruina101', 'Ruina Kang', 'ruina101@gmail.com', 5554444, 2);


COMMIT TRANSACTION;
