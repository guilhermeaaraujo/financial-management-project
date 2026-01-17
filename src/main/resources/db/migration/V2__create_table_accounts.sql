CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    balance DECIMAL(15, 2) DEFAULT 0.00,
    type VARCHAR(20),
    user_id BIGINT NOT NULL,

    CONSTRAINT fk_account_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
)