TRUNCATE TABLE connexion, app_transaction, app_user RESTART IDENTITY CASCADE;

INSERT INTO app_user (username, email, password) VALUES
('shiori', 'shiori@gmail.com', '$2y$10$h6sZFNfFPRWOzXGg.zkp/e87cIpXnztlN/B8bWDuD/KacGrR/B/EO'),
('leopold', 'leopold@gmail.com', '$2y$10$66h0aBUoEWnahXUnUST3p.EpA6RsKRnbxlP49UQ2vnOX6FhLodP/C'),
('mathilde', 'mathilde@gmail.com', '$2y$10$DEVsSc.ekuwn3Hd4xK66W.o/J/nUZECRWKTsD/SiRqd0N2X3XTti6'),
('stephane', 'stephane@gmail.com', '$2y$10$pj05nNZDjO1B0Gzem.vPAOkuw.lr.1mH3itxNvsIXln4vfyH/MrKa');

INSERT INTO app_transaction (sender_id, receiver_id, description, amount, date_transaction) VALUES
(1, 2, 'Paiement café', 4.50, CURRENT_DATE),
(2, 1, 'Remboursement', 4.50, CURRENT_DATE);

INSERT INTO connexion (user_id_1, user_id_2) VALUES
(1, 2),
(1, 4);
