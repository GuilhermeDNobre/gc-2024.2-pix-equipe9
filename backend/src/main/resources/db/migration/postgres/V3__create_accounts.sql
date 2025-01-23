CREATE TABLE accounts (
    idaccounts          UUID         NOT NULL,
    user_id             UUID         NOT NULL,
    institution         VARCHAR(255) NOT NULL,
    account_agency      INTEGER      NOT NULL,
    account_number      INTEGER      NOT NULL,
    account_type        VARCHAR(255) NOT NULL,
    balance             FLOAT,
    four_digit_password INTEGER      NOT NULL,
    six_digit_password  INTEGER      NOT NULL,
    CONSTRAINT pk_accounts PRIMARY KEY (idaccounts),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);
