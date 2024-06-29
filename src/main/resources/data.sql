INSERT INTO login (pseudo, email, password, role)
VALUES ('admin', 'admin@list.com', '$2a$10$WdoexxxliEMQMZQW8HQs1em71dj1kPnUtNUCqaGOFURT25bgXa3B2', 'ROLE_ADMIN'),
       ('userOne', 'userOne@list.com', '$2a$10$.0s3V.ElJrzkTzgbrGCqd.D2cjVeFSR2nWt3FKDUtREXXhg4U2mLK', 'ROLE_USER'),
       ('userTwo', 'userTwo@list.com', '$2a$10$7Ph7BxkL3O6YH8uBlajLk.FzW.ZrRTdHVBDZCoYQgTV9Tdn3J/a.C', 'ROLE_USER');

INSERT INTO admin (os, browser, login_id)
VALUES ('Windows', 'Firefox', 1);

INSERT INTO user (first_name, last_name, created_at, picture, address, city, zip_code, status, login_id)
VALUES ('Jeremy', 'Grégoire', '2024-04-08T19:52:02', 'https://storage.googleapis.com/quest_editor_uploads/1rtbgvXNRdje4p0Vsj7TJVVtS9lru6AK.jpg', '171 Rue Lucien Faure', 'Bordeaux', '33300', 'activated', 2),
        ('Sylvain', 'Bonnaure', '2024-06-11T19:52:02', 'https://storage.googleapis.com/quest_editor_uploads/1rtbgvXNRdje4p0Vsj7TJVVtS9lru6AK.jpg', '10 Rue de la paix', 'Bordeaux', '33100', 'activated', 3);

INSERT INTO category (name, created_at)
VALUES ('Épicerie salée', NOW()), ('Poisson', NOW()), ('Viande', NOW());

INSERT INTO item (name, quantity, category_id)
VALUES ('Pâte', 1, 1), ('Riz', 2, 1), ('Saumon', 0, 2), ('Poulet', 2, 3), ('Boeuf', 2, 3);

INSERT INTO market (name, size, place)
VALUES ('Carrefour', 'Hyper market', 'Lormont');

INSERT INTO shop (created_at, user_id)
VALUES (NOW(), 1), (NOW(), 1), (NOW(), 2);

INSERT INTO invoice (created_at, total, market_id, shop_id, user_id)
VALUES (NOW(), 86, 1, 1, 1), (NOW(), 77, 1, 2, 2), (NOW(), 112, 1, 3, 2);

INSERT INTO shop_items (shop_id, items_id)
VALUES (1, 1), (2, 2), (3, 3), (3, 4), (3, 5);