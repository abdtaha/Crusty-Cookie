SET FOREIGN_KEY_CHECKS=0;

-- Almond Delight --
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Almond Delight', 'Butter', 400, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Almond Delight', 'Chopped almonds', 279, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Almond Delight', 'Cinnamon', 10, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Almond Delight', 'Flour', 400, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Almond Delight', 'Sugar', 270, 'g');

-- Amneris --
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Amneris', 'Butter', 250, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Amneris', 'Eggs', 250, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Amneris', 'Marzipan', 750, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Amneris', 'Potato starch', 25, 'g'); 
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Amneris', 'Wheat flour', 25, 'g');

-- Berliner --
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Berliner', 'Butter', 250, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Berliner', 'Chocolate', 50, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Berliner', 'Eggs', 50, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Berliner', 'Flour', 350, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Berliner', 'Icing sugar', 100, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Berliner', 'Vanilla sugar', 5, 'g');

-- Nut Cookie --

INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Nut Cookie', 'Bread crumbs', 125, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Nut Cookie', 'Chocolate', 50, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Nut Cookie', 'Egg whites', 350, 'dl');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Nut Cookie', 'Fine-ground nuts', 750, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Nut Cookie', 'Ground, roasted nuts', 625, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Nut Cookie', 'Sugar', 375, 'g');

-- Nut Ring --
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Nut Ring', 'Butter', 450, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Nut Ring', 'Flour', 450, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Nut Ring', 'Roasted, chopped nuts', 225, 'g');

-- Tango --
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Tango', 'Butter', 200, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Tango', 'Flour', 300, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Tango', 'Sodium bicarbonate', 4, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Tango', 'Sugar', 250, 'g');
INSERT INTO Recipes(cookieName, ingredientName, amount, unit)
VALUES('Tango', 'Vanilla', 2, 'g');

-- Inserts data into the Customers table --
INSERT INTO Customers(name, address) 
VALUES('Bjudkakor AB', 'Ystad'),
('Finkakor AB', 'Helsingborg'),
('Gästkakor AB', 'Hässleholm'),
('Kaffebröd AB', 'Landskrona'),
('Kalaskakor AB', 'Trelleborg'),
('Partykakor AB', 'Kristianstad'),
('Skånekakor AB', 'Perstorp'),
('Småbröd AB', 'Malmö');

-- Inserts data into the Products table --
INSERT INTO Cookies(cookieName)
VALUES('Almond delight'),
('Amneris'),
('Berliner'),
('Nut cookie'),
('Nut ring'),
('Tango');

-- Inserts data into the Ingredients table --
INSERT INTO Raw_materials(ingredientName, storedAmount, unit)
VALUES('Bread crumbs', 500000, 'g'),
('Butter', 500000, 'g'),
('Chocolate', 500000, 'g'),
('Chopped almonds', 500000, 'g'),
('Cinnamon', 500000, 'g'),
('Egg whites', 500000, 'ml'),
('Eggs', 500000, 'g'),
('Fine-ground nuts', 500000, 'g'),
('Flour', 500000, 'g'),
('Ground, roasted nuts', 500000, 'g'),
('Icing sugar', 500000, 'g'),
('Marzipan', 500000, 'g'),
('Potato starch', 500000, 'g'),
('Roasted, chopped nuts', 500000, 'g'),
('Sodium bicarbonate', 500000, 'g'),
('Sugar', 500000, 'g'),
('Vanilla sugar', 500000, 'g'),
('Vanilla', 500000, 'g'),
('Wheat flour', 500000, 'g');

SET FOREIGN_KEY_CHECKS=1;