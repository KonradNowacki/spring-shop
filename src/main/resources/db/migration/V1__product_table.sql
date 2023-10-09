CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    price      INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
