INSERT INTO login (pseudo, email, password, role)
VALUES ('Omar', 'omar@list.com', '$2a$10$Qz5rO4LcTxmpY0khfzLRRec4w2lOeRK5rKW2zhpknKm8XgvDedtKS', 'ROLE_ADMIN'),
       ('userOne', 'userOne@list.com', '$2a$10$.0s3V.ElJrzkTzgbrGCqd.D2cjVeFSR2nWt3FKDUtREXXhg4U2mLK', 'ROLE_USER');

INSERT INTO admin (os, browser, login_id)
VALUES ('Windows', 'Firefox', 1);

INSERT INTO user (first_name, last_name, created_at, picture, address, city, zip_code, status, login_id)
VALUES ('John', 'Doe', '2024-06-11T19:52:02', 'b921e6fc-2231-413c-a813-5a06b16dd73e_male_01.svg', '10 Rue de la paix', 'Bordeaux', '33100', 'ACTIVATED', 2);

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
VALUES ('Ail', 1), ('Ananas', 1), ('Abricot', 1), ('Avocat', 1), ('Betterave', 1), ('Brocoli', 1), ('Carotte', 1), ('Citron', 1), ('Concombre', 1), ('Courge', 1),
       ('Courgette', 1), ('Endive', 1), ('Haricot vert', 1), ('Kiwi', 1), ('Mangue', 1), ('Menthe', 1), ('Melon', 1), ('Myrtille', 1), ('Navet', 1), ('Nectarine', 1), ('Oignon', 1),
       ('Pastèque', 1), ('Pêche', 1), ('Poireau', 1), ('Poivron', 1), ('Pomme', 1), ('Pomme de terre', 1), ('Piment', 1), ('Radis', 1), ('Salade', 1), ('Tomate', 1),

       ('Agneau', 2), ('Bar', 2), ('Boeuf', 2), ('Cabillaud', 2), ('Cordon bleu', 2), ('Daurade', 2), ('Dinde', 2), ('Lapin', 2), ('Magret de canard', 2), ('Maquereau', 2),
       ('Merguez', 2), ('Merlan', 2), ('Poulet', 2), ('Saumon', 2), ('Sardine', 2), ('Sole', 2), ('Thon', 2), ('Truite', 2), ('Veau', 2),

       ('Baguette', 3), ('Charlotte aux fraises', 3), ('Fondant', 3), ('Flan', 3), ('Framboisier', 3), ('Fraisier', 3), ('Gâteau basque', 3), ('Pain burger', 3), ('Pain de mie', 3),
       ('Paris-brest', 3), ('Toast', 3),

       ('Beurre', 4), ('Boursin', 4), ('Brie', 4), ('Camembert', 4), ('Chaussée aux moines', 4), ('Comté', 4), ('Coulommier', 4), ('Crème caramel', 4), ('Crème fraîche', 4),
       ('Crème légère', 4), ('Crème liquide', 4), ('Emmental', 4), ('Emmental râpé', 4), ('Feta', 4), ('Fondue', 4), ('Fromage à tartiner', 4), ('Fromage Brebis', 4),
       ('Fromage Chèvre', 4), ('Fromage Raclette', 4), ('Lait aromatisé', 4), ('Lait demi-écrémé', 4), ('Lait Entier', 4), ('Lait Fermenté', 4), ('Mascarpone', 4), ('Morbier', 4),
       ('Mozzarella', 4), ('Mousse Chocolat', 4), ('Oeuf', 4), ('Roquefort', 4), ('Tomme', 4), ('Tartiflette', 4), ('Yaourt chocolat', 4), ('Yaourt nature', 4),

       ('Apéritif', 5), ('Bagel', 5), ('Caviar', 5), ('Gnocchi', 5), ('Lasagne', 5), ('Pappardelle', 5), ('Pâte à pizza', 5), ('Pâte brisée', 5), ('Pâte feuilletée', 5),
       ('Pizza 4 fromages', 5), ('Quenelle', 5), ('Ravioli', 5), ('Sandwich', 5), ('Tagliatelle', 5), ('Wrap', 5),

       ('Boulette', 6), ('Cabillaud pané', 6), ('Croquette de poisson', 6), ('Feuilleté', 6), ('Frite', 6), ('Fruit', 6), ('Glace', 6),
       ('Limande', 6), ('Nugget', 6), ('Petit four', 6), ('Pizza', 6), ('Poisson pané', 6), ('Pomme rissolée', 6), ('Quiche', 6), ('Sorbet', 6), ('Steak haché', 6), ('Tarte', 6),

       ('Bière', 7), ('Café', 7), ('Champagne', 7), ('Cidre', 7), ('Cocktail', 7), ('Cola', 7), ('Eau', 7), ('Eau aromatisée', 7), ('Eau pétillante', 7), ('Jus', 7),
       ('Limonade', 7), ('Punch', 7), ('Rosé', 7), ('Sirop', 7), ('Soda', 7), ('Thé glacé', 7), ('Tonic', 7), ('Vin', 7),

       ('Barbecue', 8), ('Biggy burger', 8), ('Blé', 8), ('Bouillon', 8), ('Bretzel', 8), ('Cacahuète', 8), ('Champignon', 8), ('Chips', 8), ('Concentré de tomate', 8),
       ('Cornichon', 8), ('Couscous', 8), ('Cracker', 8), ('Croûton', 8), ('Epice', 8), ('Gaspacho', 8), ('Haricot rouge', 8),
       ('Huile de tournesol', 8), ('Ketchup', 8), ('Mais', 8), ('Mayonnaise', 8), ('Moutarde', 8), ('Olive', 8), ('Pâtes', 8), ('Pistache', 8), ('Poivre', 8),
       ('Pulpe de tomate', 8), ('Purée', 8), ('Purée de tomate', 8), ('Riz', 8), ('Sauce Algérienne', 8), ('Sauce Andalouse', 8), ('Sauce aigre douce', 8),
       ('Sauce burger', 8), ('Sauce curry', 8), ('Sauce deluxe', 8), ('Sauce épicée', 8), ('Sauce harissa', 8), ('Sauce poivre', 8), ('Sauce samurai', 8), ('Sauce Sriracha', 8),
       ('Sauce soja', 8), ('Sauce tomate', 8), ('Sauce pomme frite', 8), ('Sel', 8), ('Soupe', 8), ('Vinaigrette', 8),

       ('Amande en poudre', 9), ('Barre chocolatée', 9), ('Biscotte', 9), ('Biscuit', 9), ('Bonbon', 9), ('Boudoir', 9), ('Brownie', 9), ('Café grain', 9), ('Café moulu', 9),
       ('Café soluble', 9), ('Capsule', 9), ('Caramel beurre salé', 9), ('Cerneaux de noix', 9), ('Céréale', 9), ('Chocolat', 9), ('Chocolat en poudre', 9),
       ('Chocolat pâtissier', 9), ('Compote de fruit', 9), ('Compote de pomme', 9), ('Compote pomme poire', 9), ('Cookie', 9), ('Crêpe', 9), ('Dosette', 9), ('Edulcorant', 9),
       ('Farine', 9), ('Ferment lactique', 9), ('Gaufre', 9), ('Infusion', 9), ('Lait concentré', 9), ('Lait en poudre', 9), ('Levure', 9), ('Levure chimique', 9),
       ('Madeleine', 9), ('Miel', 9), ('Pain grillé', 9), ('Pâte à tartiner', 9), ('Pépite de chocolat', 9), ('Sirop de glucose', 9), ('Sucre de canne', 9),
       ('Sucre en poudre', 9), ('Sucre morceaux', 9), ('Sucre vanillé', 9), ('Tablette de chocolat', 9), ('Thé', 9),

       ('Adoucissant', 10), ('Anti-insecte volant', 10), ('Anti-mouche', 10), ('Anti-moustique', 10), ('Antimite', 10), ('Attrape-poussière', 10), ('Balai', 10),
       ('Balayette', 10), ('Bassine', 10), ('Blanchissant Oxygéné', 10), ('Bloc WC', 10), ('Bougie anti-volant', 10), ('Bougie parfumée', 10), ('Brosse chaussure', 10),
       ('Brosse vaisselle', 10), ('Brosse WC', 10), ('Capsule lessive', 10), ('Dépoussiérant bois', 10), ('Déboucheur canalisation', 10), ('Désodorisant', 10),
       ('Détachant', 10), ('Eau de javel', 10), ('Eponge', 10), ('Eponge inox', 10), ('Essuie-tout', 10), ('Gant', 10), ('Gel WC', 10), ('Insecticide', 10), ('Lessive', 10),
       ('Lessive en poudre', 10), ('Lingette anti-décoloration', 10), ('Lingette désinfectante', 10), ('Liquide vaisselle', 10), ('Mouchoir', 10), ('Nettoyant vitre', 10),
       ('Papier tue-mouche', 10), ('Papier WC', 10), ('Pâte Nettoyante', 10), ('Pelle', 10), ('Pelle balayette', 10), ('Piège à mite', 10), ('Poudre anti-calcaire', 10),
       ('Raclette', 10), ('Reblanchisseur', 10), ('Sac poubelle', 10), ('Sel régénérant', 10), ('Serpillère', 10), ('Spray détachant', 10), ('Tablette lave-vaisselle', 10),
       ('Teinture textile', 10),

       ('Après-shampoing', 11), ('Anti-cerne', 11), ('Alcool modifié', 11), ('Bain de bouche', 11), ('Bande adhésive', 11), ('Bande cire', 11),
       ('Brosette interdentaire', 11), ('Brumisateur', 11), ('Brosse à dent', 11), ('Brosse à dent électrique', 11), ('Cire', 11), ('Cire dépilatoire', 11),
       ('Coloration cheveux', 11), ('Compresse stérile', 11), ('Comprimé nettoyant', 11), ('Coton disque', 11), ('Coton-tiges', 11), ('Crème adhésive', 11),
       ('Crème lavante', 11), ('Crème main', 11), ('Crème visage', 11), ('Culotte', 11), ('Déodorant', 11), ('Dentifrice', 11), ('Dissolvant', 11), ('Eau de cologne', 11),
       ('Eau oxygénée', 11), ('Etui brosse', 11), ('Fil dentaire', 11), ('Fond de teint', 11), ('Gel désinfectant', 11), ('Gel douche', 11), ('Gel intime', 11),
       ('Gel lubrifiant', 11), ('Gouttes oculaires', 11), ('Huile cheveux', 11), ('Huile de barbe', 11), ('Huile de jojoba', 11), ('Huile de massage', 11), ('Laque', 11),
       ('Lait corporel', 11), ('Lait démaquillant', 11), ('Lame de rasoir', 11), ('Mascara', 11), ('Mousse à raser', 11), ('Pansement', 11),
       ('Préservatif', 11), ('Protège-slip', 11), ('Protection auditive', 11), ('Rasoir', 11), ('Savon', 11), ('Sérum physiologique', 11), ('Sérum visage', 11),
       ('Serviette hygiénique', 11), ('Shampoing', 11), ('Shampoing anti-poux', 11), ('Shampoing sec', 11), ('Solution antiseptique', 11), ('Solution lentille', 11),
       ('Spray coiffant', 11), ('Spray nasal', 11), ('Stick à lèvres', 11), ('Tampon', 11), ('Test de grossesse', 11), ('Vernis à ongles', 11),

       ('Aliment volaille', 12), ('Arbre à chat', 12), ('Bâton herbe', 12), ('Balle', 12), ('Boule de graisse', 12), ('Collier', 12),
       ('Croquette', 12), ('Flocons pour poisson', 12), ('Friandise', 12), ('Graine pour oiseau', 12), ('Griffoir', 12), ('Huile de saumon', 12), ('Jouet', 12),
       ('Lait pour chat', 12), ('Litière', 12), ('Niche', 12), ('Panier animaux', 12), ('Pâtée', 12), ('Souris', 12);

INSERT INTO market (name, size, picture)
VALUES ('Aldi', 'Super marché', 'cc68d555-f8b7-43d2-8e60-732426c84061_aldi.svg'),
       ('Auchan', 'Hyper marché', '0105cc16-e0e4-47ab-94bf-4dba6ba39cfc_auchan.svg'),
       ('Carrefour', 'Hyper marché', 'e1f2d910-b197-41c3-bb45-225b96198b4a_carrefour.svg'),
       ('Carrefour City', 'Super marché', 'f8c292cf-72fa-41c1-b101-8f7d3341239b_carrefour_city.svg'),
       ('Carrefour Contact', 'Super marché', '87413b9c-73d5-405b-974d-255d20a80763_carrefour_contact.svg'),
       ('Carrefour Market', 'Super marché', '551f3719-f4a2-40a5-be17-d71830ee3bea_carrefour_market.svg'),
       ('Casino', 'Super marché', 'efc8975a-0be2-4338-84b0-48c78a817dfa_casino.svg'),
       ('E.Leclerc', 'Hyper marché', 'f5ff0315-e929-4c50-b229-1a50a04a6cca_e_leclerc.svg'),
       ('Intermarché', 'Hyper marché', 'c552a09a-cd09-4bab-a0d9-8bd8e397cbde_intermarche.svg'),
       ('Leader price', 'Super marché', '9f0ad903-6302-472f-a74c-05fa8ae9346f_leader_price.svg'),
       ('Lidl', 'Super marché', '635ad294-be4a-4ab4-96f8-0ce725237d81_lidl.svg'),
       ('Picard', 'Super marché', '1661b6ce-3c7f-4d03-896b-4fe766c5b586_picard.svg');

INSERT INTO shop (created_at, is_completed, user_id)
VALUES ('2024-07-14 10:33:53', true, 1),
       ('2023-06-22 02:45:53', true, 1),
       ('2023-01-31 14:00:53', true, 1);

INSERT INTO invoice (created_at, total, market_id, shop_id, user_id)
VALUES ('2024-07-14 10:33:53', 112, 1, 1, 1),
       ('2023-06-22 02:45:53', 222, 1, 2, 1),
       ('2023-01-31 14:00:53', 90, 1, 3, 1);

INSERT INTO shop_items (shop_id, items_id)
VALUES (1, 9), (1, 14), (1, 15),
       (2, 5), (2, 12), (2, 32), (2, 33), (2,39),
       (3, 10), (3, 42), (3, 44);