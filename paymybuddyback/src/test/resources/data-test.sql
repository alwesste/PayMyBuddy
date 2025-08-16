TRUNCATE TABLE connexion, app_transaction, app_user RESTART IDENTITY CASCADE;

INSERT INTO app_user (username, email, password) VALUES
('geremi', 'geremi@gmail.com', '$2y$10$lQHGQ7E3RpEzSR2kodPhi.CpRUNrhLKlxaTkayj4AmUmQKWpe8tN2'),
('paul', 'paul@gmail.com', '$2y$10$51Y0uldPcWD7mLdHasduvOlJ8TXmTlBkv84aUqHyLLdF7PWOZIlWW'),
('mathilde', 'mathilde@gmail.com', '$2y$10$oTCCW6fc1Qui38CnSWfacutVmN6nU.XBKrDWNvk6rL6Rg2kHeWQTe'),
('jean', 'jean@gmail.com', '$2y$10$z7JAUkLfUTpMSs0EB0CquuyK1EN4OAYXUKv4pma5aX5IVFt9POcRO');


INSERT INTO app_transaction (sender_id, receiver_id, description, amount, date_transaction) VALUES
(1, 2, 'Paiement café', 4.50, CURRENT_DATE),
(2, 1, 'Remboursement', 4.50, CURRENT_DATE);

INSERT INTO connexion (user_id_1, user_id_2) VALUES
(1, 2),
(1, 3)
