DROP TABLE IF EXISTS connexion;
DROP TABLE IF EXISTS app_transaction;
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
    id SERIAL PRIMARY KEY ,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE app_transaction (
    id SERIAL PRIMARY KEY ,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    description TEXT,
    amount DOUBLE PRECISION NOT NULL,
    date_transaction DATE NOT NULL,
    FOREIGN KEY (sender_id) REFERENCES app_user(id),
    FOREIGN KEY (receiver_id) REFERENCES app_user(id)
);

CREATE TABLE connexion (
    user_id_1 INT NOT NULL,
    user_id_2 INT NOT NULL,
    PRIMARY KEY (user_id_1, user_id_2),
    FOREIGN KEY(user_id_1) REFERENCES app_user(id),
    FOREIGN KEY(user_id_2) REFERENCES app_user(id)
);
