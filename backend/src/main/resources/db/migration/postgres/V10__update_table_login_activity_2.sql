ALTER TABLE login_activity
DROP CONSTRAINT fk_user;

ALTER TABLE login_activity
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;
