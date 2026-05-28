-- Add soft delete support to products
ALTER TABLE products ADD COLUMN deleted BOOLEAN NOT NULL DEFAULT FALSE;

-- Partial index: only index non-deleted products (WHERE clause reduces index size)
CREATE INDEX idx_products_active ON products(id) WHERE deleted = FALSE;

-- Index for name search (case-insensitive with lower())
CREATE INDEX idx_products_name_lower ON products(lower(name));
