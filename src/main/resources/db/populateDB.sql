DELETE FROM dishes;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq
RESTART WITH 100000;

INSERT INTO DISHES (DESCRIPTION, PRICE) VALUES
  ('Chicken soup', 2.5),
  ('Russian salad', 2),
  ('Borscht', 3),
  ('Beef chop', 3.2),
  ('Lamb chop', 3.4),
  ('Cacao', 1),
  ('Mushroom soup', 2.5),
  ('Vinaigrette', 2),
  ('Dumplings', 3),
  ('Chicken chop', 2.5),
  ('Coffee', 1.2);


INSERT INTO RESTAURANTS (ADDRESS) VALUES
  ('St.Petersburg, Leninskiy Prospect st., 21'),
  ('St.Petersburg, Panfilova st., 87');

