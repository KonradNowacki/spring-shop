CREATE TABLE products
(
    id  SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    price       INTEGER      NOT NULL,
    description TEXT,
    is_public   BOOLEAN      NOT NULL DEFAULT FALSE,

    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NOT NULL

--     owner_id    INTEGER      NOT NULL REFERENCES users (user_id)
);
