INSERT INTO pix_keys (id, key_value, type, date, account_id)
VALUES
    (RANDOM_UUID(), 'carlos.souza@live.com', 'EMAIL', CURRENT_DATE, (SELECT id FROM accounts WHERE number = 246810));
