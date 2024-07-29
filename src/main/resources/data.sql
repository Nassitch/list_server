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
VALUES ('Pomme', 1), ('Banane', 1), ('Kiwi', 1), ('Avocat', 1), ('Tomate', 1), ('Salade', 1), ('Pomme de terre', 1), ('Carotte', 1),
       ('Thon', 2), ('Saumon', 2),
       ('Baguette', 3), ('Charlotte aux fraises', 3),
       ('Crème liquide', 4), ('Lait', 4), ('Oeuf', 4), ('Mozzarella', 4), ('Emmental râpé', 4), ('Camembert', 4), ('Boursin', 4), ('Leerdammer', 4), ('Chaussée aux moines', 4), ('Beurre', 4), ('Compote de pomme', 4), ('Yaourt', 4),
       ('Apéritif', 5), ('Sandwich', 5), ('Pâte à pizza', 5),
       ('Glace', 6), ('Pizza', 6), ('Frite', 6),
       ('Eau', 7), ('Soda', 7), ('Thé', 7), ('Sirop', 7),
       ('Chips', 8), ('Huile de tournesol', 8), ('Pâtes', 8), ('Riz', 8), ('Purée', 8), ('Mayonnaise', 8), ('Biggy burger', 8), ('Soupe', 8),
       ('Biscuit', 9), ('Brownie', 9), ('Sucre', 9), ('Pâte à tartiner', 9), ('Chocolat', 9),
       ('Lessive', 10), ('Liquide vaisselle', 10), ('Mouchoir', 10), ('Papier toilette', 10), ('Essuie-tout', 10),
       ('Shampooing', 11), ('Gel douche', 11), ('Dentifrice', 11), ('Cire', 11), ('Coton-tiges', 11),
       ('Croquette', 12), ('Litière', 12);

INSERT INTO market (name, size, place, picture)
VALUES ('Carrefour', 'Hyper marché', 'Lormont', 'e1f2d910-b197-41c3-bb45-225b96198b4a_carrefour.svg'),
       ('Auchan', 'Hyper marché', 'Bouliac', '0105cc16-e0e4-47ab-94bf-4dba6ba39cfc_auchan.svg'),
       ('Carrefour City', 'Super marché', 'Cenon', '87413b9c-73d5-405b-974d-255d20a80763_carrefour_contact.svg');

INSERT INTO shop (created_at, is_completed, user_id)
VALUES (NOW(), true, 1), (NOW(), true, 1), (NOW(), true, 2);

INSERT INTO invoice (created_at, total, market_id, shop_id, user_id)
VALUES (NOW(), 86, 1, 1, 1), (NOW(), 77, 1, 2, 2), (NOW(), 112, 1, 3, 2);

INSERT INTO shop_items (shop_id, items_id)
VALUES (1, 1), (2, 2), (3, 9), (3, 14), (3, 15);