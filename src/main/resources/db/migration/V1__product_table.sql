CREATE TABLE categories
(
    category_id SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL
);

CREATE TABLE products
(
    product_id  SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    price       INTEGER      NOT NULL,
    description TEXT,
    is_public   BOOLEAN      NOT NULL DEFAULT FALSE,

    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NOT NULL
);

CREATE TABLE products_categories
(
    product_id  BIGINT REFERENCES products (product_id),
    category_id BIGINT REFERENCES categories (category_id),

    PRIMARY KEY (product_id, category_id)
);

-- CREATE TABLE users_roles
-- (
--     user_id BIGINT,
--     role_id BIGINT,
--
--     CONSTRAINT FOREIGN_KEY_USER
--         FOREIGN KEY (user_id)
--             REFERENCES users (user_id)
--             ON DELETE NO ACTION
--             ON UPDATE NO ACTION,
--
--     CONSTRAINT FOREIGN_KEY_ROLE
--         FOREIGN KEY (role_id)
--             REFERENCES roles (role_id)
--             ON DELETE NO ACTION
--             ON UPDATE NO ACTION
-- );
