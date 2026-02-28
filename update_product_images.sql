-- Update product images with proper URLs

UPDATE product SET image_url = 'https://justintime.in/cdn/shop/files/AX2862_1.jpg?v=1766239365&width=1200' WHERE id = 2 AND name LIKE '%Watch%';

UPDATE product SET image_url = 'https://images.pexels.com/photos/3780681/pexels-photo-3780681.jpeg?auto=compress&cs=tinysrgb&w=500' WHERE id = 1 AND name LIKE '%Cleanser%';

-- Verify updates
SELECT id, name, image_url FROM product WHERE id IN (1, 2);
