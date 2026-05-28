-- Add soft delete support to products
ALTER TABLE products ADD COLUMN deleted BOOLEAN NOT NULL DEFAULT FALSE;

-- Non-partial index for H2 compatibility (H2 does not support index with WHERE clause)
CREATE INDEX idx_products_active ON products(id, deleted);

-- Index for name search (standard name index in H2 since function-based lower(name) index is not directly supported)
CREATE INDEX idx_products_name_lower ON products(name);
