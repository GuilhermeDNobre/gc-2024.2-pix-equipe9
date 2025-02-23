CREATE TABLE accounts (
    id UUID PRIMARY KEY,
    agency INT NOT NULL,
    number INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    balance numeric DEFAULT 0.0 NOT NULL,
    password VARCHAR NOT NULL,
    user_id UUID UNIQUE,
    status VARCHAR,
    daily_value_limit DOUBLE,
    CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES users(id)
);

ALTER TABLE users
ADD CONSTRAINT fk_user_account FOREIGN KEY (account_id) REFERENCES accounts(id);
