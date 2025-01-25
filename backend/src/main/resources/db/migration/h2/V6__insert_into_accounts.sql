-- Inserindo contas com UUIDs sequenciais

INSERT INTO accounts (id, agency, number, type, balance, password, user_id)
VALUES
    ('33333333-3333-3333-3333-333333333333', 1357, 246810, 'CORRENTE', 3000.00, 1357, (SELECT id FROM users WHERE email = 'carlos.souza@gmail.com'));
