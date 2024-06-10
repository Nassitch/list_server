INSERT INTO login (pseudo, email, password, role)
VALUES ('admin', 'admin@list.com', 'administrator', 'admin'),
       ('userOne', 'userOne@list.com', 'userOne', 'user');

INSERT INTO admin (os, browser, login_id)
VALUES ('Windows', 'Firefox', 1);

INSERT INTO user (first_name, last_name, created_at, picture, address, city, zip_code, status, login_id)
VALUES ('Jeremy', 'Grégoire', '2024-06-08 19:52:02', 'https://storage.googleapis.com/quest_editor_uploads/1rtbgvXNRdje4p0Vsj7TJVVtS9lru6AK.jpg', '171 Rue Lucien Faure', 'Bordeaux', '33300', 'activate', 2);

INSERT INTO category (name, created_at)
VALUES ('Épicerie salée', NOW());

INSERT INTO item (name, quantity, category_id)
VALUES ('Pâte', 1, 1), ('Riz', 2, 1);

INSERT INTO market (name, size, place)
VALUES ('Carrefour', 'Hyper market', 'Lormont');

INSERT INTO invoice (created_at, total, market_id)
VALUES (NOW(), 86, 1), (NOW(), 77, 1);

INSERT INTO shop (item_id, user_id)
VALUES (1, 1), (2, 1);