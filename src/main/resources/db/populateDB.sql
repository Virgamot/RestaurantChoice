DELETE FROM restaurants;
DELETE FROM dishes;
ALTER SEQUENCE global_seq
RESTART WITH 100000;

INSERT INTO RESTAURANTS (ADDRESS) VALUES
  ('St.Petersburg, Leninskiy Prospect st., 21'),
  ('St.Petersburg, Panfilova st., 87');

INSERT INTO DISHES (DESCRIPTION, PRICE, RESTAURANT_ID) VALUES
  ('Chicken soup', 2.5, 100000),
  ('Russian salad', 2, 100000),
  ('Borscht', 3, 100000),
  ('Beef chop', 3.2, 100000),
  ('Lamb chop', 3.4, 100000),
  ('Cacao', 1, 100000),
  ('Mushroom soup', 2.5, 100001),
  ('Vinaigrette', 2, 100001),
  ('Dumplings', 3, 100001),
  ('Chicken chop', 2.5, 100001),
  ('Coffee', 1.2, 100001);

