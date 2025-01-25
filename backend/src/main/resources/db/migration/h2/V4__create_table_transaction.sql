CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    sender_account_id UUID NOT NULL,
    receiver_account_id UUID NOT NULL,
    transfer_value FLOAT NOT NULL,
    finished_at TIMESTAMP,
    send_date DATE,
    created_at TIMESTAMP,
    status VARCHAR,
    CONSTRAINT fk_transaction_sender FOREIGN KEY (sender_account_id) REFERENCES accounts(id),
    CONSTRAINT fk_transaction_receiver FOREIGN KEY (receiver_account_id) REFERENCES accounts(id)
);