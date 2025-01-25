CREATE TABLE users (
  user_id UUID NOT NULL,
   name VARCHAR(255),
   tax_id VARCHAR(255),
   email VARCHAR(255),
   password VARCHAR(255),
   birth_date date,
   access varchar,
   status varchar,
   CONSTRAINT pk_users PRIMARY KEY (user_id)
);