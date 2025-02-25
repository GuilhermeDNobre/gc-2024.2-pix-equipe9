-- Inserindo usuários com UUIDs sequenciais

INSERT INTO users (id, name, cpf, email, password, birth_date, status, access, account_id)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'João Silva', '123.456.789-00', 'joao@gmail.com', 'senha123', '1990-05-12', 'ACTIVE', 'USER', NULL),
    ('22222222-2222-2222-2222-222222222222', 'Maria Oliveira', '987.654.321-00', 'maria@gmail.com', 'senha456', '1985-07-30', 'DELETED', 'ADMIN', NULL),
    ('33333333-3333-3333-3333-333333333333', 'Carlos Souza', '111.222.333-44', 'carlos@gmail.com', 'senha789', '1992-11-20', 'ACTIVE', 'USER', NULL);
