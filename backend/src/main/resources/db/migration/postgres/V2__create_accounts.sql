CREATE TABLE accounts (
    id UUID PRIMARY KEY,
    agency INT NOT NULL,
    number INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    balance NUMERIC DEFAULT 0.0 NOT NULL,
    password VARCHAR NOT NULL,
    user_id UUID UNIQUE,
    status VARCHAR
);
