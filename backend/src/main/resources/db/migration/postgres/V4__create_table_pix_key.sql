CREATE TABLE pix_key (
  id UUID NOT NULL,
   key VARCHAR(255),
   type VARCHAR(255),
   date date,
   user_id UUID NOT NULL,
   CONSTRAINT pk_pixkey PRIMARY KEY (id)
);

ALTER TABLE pix_key ADD CONSTRAINT FK_PIXKEY_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);