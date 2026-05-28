-- Step 1: Add nullable first (safe — existing rows get NULL, no lock needed)
ALTER TABLE products ADD COLUMN slug VARCHAR(255);

-- Step 2: Backfill existing rows with a generated slug
UPDATE products SET slug = lower(replace(name, ' ', '-'));

-- Step 3: Now safe to add NOT NULL + UNIQUE constraint
ALTER TABLE products ALTER COLUMN slug SET NOT NULL;
CREATE UNIQUE INDEX idx_products_slug ON products(slug);

-- This 3-step pattern avoids a table lock on large tables
-- (Never do: ADD COLUMN slug VARCHAR NOT NULL in one step on a live table)
