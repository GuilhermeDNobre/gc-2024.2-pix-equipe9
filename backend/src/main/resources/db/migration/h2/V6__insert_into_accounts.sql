-- Inserindo contas com UUIDs sequenciais

INSERT INTO accounts (id, agency, number, type, balance, password, user_id, status)
VALUES
    ('33333333-3333-3333-3333-333333333333', 1357, 246810, 'CORRENTE', 3000.00, '1357', (SELECT id FROM users WHERE email = 'carlos@gmail.com'),'ACTIVE'),
    ('44444444-4444-4444-4444-444444444444', 2468, 135791, 'CORRENTE', 4500.00,'2468', (SELECT id FROM users WHERE email = 'maria@gmail.com'), 'ACTIVE');
