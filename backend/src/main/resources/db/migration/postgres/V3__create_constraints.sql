-- Constraint de chave estrangeira na tabela accounts
ALTER TABLE accounts
ADD CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES users(id);

-- Constraint de chave estrangeira na tabela users
ALTER TABLE users
ADD CONSTRAINT fk_user_account FOREIGN KEY (account_id) REFERENCES accounts(id);
