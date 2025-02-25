CREATE TABLE notification (
      notification_id UUID PRIMARY KEY,
      user_id UUID NOT NULL,
      message TEXT NOT NULL,
      finished_at TIMESTAMP NOT NULL,
      CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id)
);