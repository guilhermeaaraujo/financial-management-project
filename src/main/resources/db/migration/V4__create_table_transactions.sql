CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(15, 2),
    transaction_date TIMESTAMP,

    account_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    CONSTRAINT fk_transaction_account
        FOREIGN KEY (account_id)
        REFERENCES accounts(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_transaction_category
        FOREIGN KEY (category_id)
        REFERENCES categories(id)
)