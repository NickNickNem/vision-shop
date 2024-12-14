insert into category(category_name) value('Fiction');
insert into category(category_name) value('Non-Fiction');
insert into category(category_name) value('Science-Fiction');
insert into category(category_name) value('Fantasy');
insert into category(category_name) value('Light Novel');
insert into category(category_name) value('Fiction2');
insert into category(category_name) value('Non-Fiction2');
insert into category(category_name) value('Science-Fiction2');
insert into category(category_name) value('Fantasy2');
insert into category(category_name) value('Light Novel2');

insert into products(product_id, product_name, publisher_name, author_name, translator_name, purchase_price, unit_price, discount_price, selling_price, description, thumbnail_image_data, product_image_data, product_status, category_id) values(1, 'product1', 'publisher1', 'a_name1', 't_name1', 10000, 10000, 0, 10000, 'explanation', 't_data1', 'p_data1', 'Active', 1);
insert into products(product_id, product_name, publisher_name, author_name, translator_name, purchase_price, unit_price, discount_price, selling_price, description, thumbnail_image_data, product_image_data, product_status, category_id) values(2, 'product2', 'publisher2', 'a_name2', 't_name2', 100000, 100000, 10000, 90000, 'explanation', 't_data2', 'p_data2', 'InActive', 2);
insert into products(product_id, product_name, publisher_name, author_name, translator_name, purchase_price, unit_price, discount_price, selling_price, description, thumbnail_image_data, product_image_data, product_status, category_id) values(3, 'product3', 'publisher3', 'a_name3', 't_name3', 12500, 12500, 2500, 10000, 'explanation', 't_data3', 'p_data3', 'Active', 3);
insert into products(product_id, product_name, publisher_name, author_name, translator_name, purchase_price, unit_price, discount_price, selling_price, description, thumbnail_image_data, product_image_data, product_status, category_id) values(4, 'product4', 'publisher4', 'a_name4', 't_name4', 50000, 50000, 0, 50000, 'explanation', 't_data4', 'p_data4', 'InActive', 4);
insert into products(product_id, product_name, publisher_name, author_name, translator_name, purchase_price, unit_price, discount_price, selling_price, description, thumbnail_image_data, product_image_data, product_status, category_id) values(5, 'product5', 'publisher5', 'a_name5', 't_name5', 1, 1, 0, 1, 'explanation', 't_data5', 'p_data5', 'Active', 5);