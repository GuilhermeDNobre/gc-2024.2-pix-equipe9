CREATE TABLE login_activity (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    ip_address VARCHAR(50) NOT NULL,
    location VARCHAR(255) NOT NULL,
    login_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id)
);