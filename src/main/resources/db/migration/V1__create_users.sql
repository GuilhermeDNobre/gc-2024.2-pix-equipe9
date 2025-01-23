CREATE TABLE users (
  user_id UUID NOT NULL,
   name VARCHAR(255),
   tax_id VARCHAR(255) UNIQUE,
   email VARCHAR(255) UNIQUE,
   password VARCHAR(255),
   birth_date date,
   CONSTRAINT pk_users PRIMARY KEY (user_id)
);