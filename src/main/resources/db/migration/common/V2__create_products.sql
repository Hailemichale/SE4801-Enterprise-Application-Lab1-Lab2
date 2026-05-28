CREATE TABLE products (
    id          BIGSERIAL    PRIMARY KEY,
    name        VARCHAR(200) NOT NULL,
    description TEXT,
    price       NUMERIC(10,2) NOT NULL CHECK (price >= 0),
    stock       INTEGER       NOT NULL DEFAULT 0 CHECK (stock >= 0),
    category_id BIGINT        REFERENCES categories(id) ON DELETE SET NULL,
    created_at  TIMESTAMP     NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP     NOT NULL DEFAULT NOW()
);

-- Index on category_id for JOIN performance
CREATE INDEX idx_products_category ON products(category_id);

COMMENT ON TABLE products IS 'ShopWave product catalogue';
