TRUNCATE TABLE connexion, app_transaction, app_user RESTART IDENTITY CASCADE;

INSERT INTO app_user (username, email, password) VALUES
('geremi', 'geremi@gmail.com', 'password1'),
('paul', 'paul@gmail.com', 'password2'),
('mathilde', 'mathilde@gmail.com', 'password3'),
('jean', 'jean@gmail.com', 'password4');


INSERT INTO app_transaction (sender_id, receiver_id, description, amount, date_transaction) VALUES
(1, 2, 'Paiement café', 4.50, CURRENT_DATE),
(2, 1, 'Remboursement', 4.50, CURRENT_DATE);

INSERT INTO connexion (user_id_1, user_id_2) VALUES
(1, 2),
(1, 3)
