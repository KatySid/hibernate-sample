BEGIN;

DROP TABLE IF EXISTS simple_products CASCADE;
CREATE TABLE simple_products (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO simple_products (title, price) VALUES
('box', 10),
('sphere', 20),
('maul', 100),
('door', 50),
('door2', 250),
('camera', 500);


COMMIT;