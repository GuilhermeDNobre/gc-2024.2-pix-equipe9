CREATE TABLE users (
    user_id UUID PRIMARY KEY,               -- Identificador único (UUID)
    name VARCHAR(255),                      -- Nome do usuário
    cpf VARCHAR(11),                        -- CPF do usuário
    email VARCHAR(255),                     -- Email do usuário
    password VARCHAR(255),                  -- Senha do usuário
    birth_date DATE,                        -- Data de nascimento
    access VARCHAR(50) NOT NULL,            -- Nível de acesso (USER ou ADMIN)
    active BOOLEAN
);
