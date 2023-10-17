CREATE TABLE category
(
    id  SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL
--     CONSTRAINT FOREIGN_KEY_PRODUCT_CATEGORY FOREIGN KEY (product_category_id) REFERENCES products (product_id)
)
