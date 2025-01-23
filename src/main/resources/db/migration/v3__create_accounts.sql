CREATE TABLE accounts
(
    idaccounts          UUID         NOT NULL UNIQUE,
    user_id             UUID         NOT NULL UNIQUE,
    account_agency      INTEGER      NOT NULL,
    account_number      INTEGER      NOT NULL UNIQUE,
    account_type        VARCHAR(255) NOT NULL,
    balance             FLOAT,
    four_digit_password INTEGER      NOT NULL,
    six_digit_password  INTEGER      NOT NULL,
    CONSTRAINT pk_accounts PRIMARY KEY (idaccounts)
    CONSTRAINT fk_user FOREIGN KEY (user_id)
    REFERENCES users(user_id)
);
