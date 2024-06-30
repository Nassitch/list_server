INSERT INTO login (pseudo, email, password, role)
VALUES ('admin', 'admin@list.com', '$2a$10$WdoexxxliEMQMZQW8HQs1em71dj1kPnUtNUCqaGOFURT25bgXa3B2', 'ROLE_ADMIN'),
       ('userOne', 'userOne@list.com', '$2a$10$.0s3V.ElJrzkTzgbrGCqd.D2cjVeFSR2nWt3FKDUtREXXhg4U2mLK', 'ROLE_USER'),
       ('userTwo', 'userTwo@list.com', '$2a$10$7Ph7BxkL3O6YH8uBlajLk.FzW.ZrRTdHVBDZCoYQgTV9Tdn3J/a.C', 'ROLE_USER');

INSERT INTO admin (os, browser, login_id)
VALUES ('Windows', 'Firefox', 1);

INSERT INTO user (first_name, last_name, created_at, picture, address, city, zip_code, status, login_id)
VALUES ('Mahdi', 'Mcheik', '2024-04-08T19:52:02', 'c4bc9d19-cbba-4d6b-b36f-98cad47db53c_male_03.svg', '1 Rue Honoré Daumier', 'Talence', '33400', 'ACTIVATED', 2),
        ('John', 'Doe', '2024-06-11T19:52:02', 'b921e6fc-2231-413c-a813-5a06b16dd73e_male_01.svg', '10 Rue de la paix', 'Bordeaux', '33100', 'ACTIVATED', 3);

INSERT INTO category (name, picture, created_at)
VALUES ('Fruits & Légumes', '6b1657fe-9549-441b-bc26-1bdb2a37d269_fruit_vegetable.svg', NOW()),
       ('Viandes & Poissons', 'b59b6a31-106d-4a51-999c-58ba57e5468f_meat_fish.svg', NOW()),
       ('Pains & Pâtisseries', 'd5f15647-a43a-4300-8685-88b0b269c7c6_bread_patisserie.svg', NOW()),
       ('Crèmerie & Produits laitiers', '830b365e-a57a-4c40-919b-79aabe225a1b_creamery__dairy_product.svg', NOW()),
       ('Charcuterie & Traiteur', 'eb00e943-21d2-475e-8770-0882f3c6e412_charcuterie_delicatessen.svg', NOW()),
       ('Surgelés', 'a6096d26-d919-4881-9063-08a0a2e2070e_frozen_food.svg', NOW()),
       ('Boissons', '500c99f9-db3b-4924-a247-a046080acfb4_drink.svg', NOW()),
       ('Épicerie salée', '967ef651-d0f4-47a4-b102-0586d7841751_savoury_grocerie.svg', NOW()),
       ('Épicerie sucrée', '165fb331-3d17-459d-9b4c-5f2261e5fac1_sweet_grocerie.svg', NOW()),
       ('Entretien & Nettoyage', '45f17829-5d88-4ae6-8f3b-c38c75c929f3_maintenance_cleaning.svg', NOW()),
       ('Hygiène & Beauté', '8e41732f-ee3a-41ea-832e-47e8553207ef_hygiene_beauty.svg', NOW()),
       ('Animalerie', '6c9940d5-aa49-42f6-bc3d-c20942fb9fb3_pet_shop.svg', NOW());

INSERT INTO item (name, category_id)
VALUES ('Pâte', 1), ('Riz', 1), ('Saumon', 2), ('Poulet', 2), ('Boeuf', 2);

INSERT INTO market (name, size, place)
VALUES ('Carrefour', 'Hyper market', 'Lormont');

INSERT INTO shop (created_at, user_id)
VALUES (NOW(), 1), (NOW(), 1), (NOW(), 2);

INSERT INTO invoice (created_at, total, market_id, shop_id, user_id)
VALUES (NOW(), 86, 1, 1, 1), (NOW(), 77, 1, 2, 2), (NOW(), 112, 1, 3, 2);

INSERT INTO shop_items (shop_id, items_id)
VALUES (1, 1), (2, 2), (3, 3), (3, 4), (3, 5);