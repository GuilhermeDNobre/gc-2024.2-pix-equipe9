CREATE TABLE pix_key (
    id UUID NOT NULL,
    "key" VARCHAR(255),
    type VARCHAR(255),
    date DATE,
    user_id UUID NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_pixkey_on_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);
