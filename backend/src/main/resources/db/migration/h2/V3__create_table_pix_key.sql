CREATE TABLE pix_keys (
    id UUID PRIMARY KEY,
    key_value VARCHAR(255) UNIQUE NOT NULL,
    type VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    account_id UUID NOT NULL,
    CONSTRAINT fk_pixkeys_account FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);