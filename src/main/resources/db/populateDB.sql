DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM dishes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO RESTAURANTS (NAME,ADDRESS) VALUES
  ('Lenin','St.Petersburg, Leninskiy Prospect st., 21'),
  ('Rain','St.Petersburg, Panfilova st., 87');

INSERT INTO DISHES (NAME, PRICE, RESTAURANT_ID) VALUES
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

/*
{noop} is needed to avoid PasswordEncoder exception
https://www.mkyong.com/spring-boot/spring-security-there-is-no-passwordencoder-mapped-for-the-id-null/
 */
INSERT INTO users (name, email, password, restaurant_id)
VALUES ('User', 'user@yandex.ru', '{noop}password', NULL);

INSERT INTO users (name, email, password, restaurant_id)
VALUES ('Admin', 'admin@gmail.com', '{noop}admin', NULL);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100013),
  ('ROLE_ADMIN', 100014),
  ('ROLE_USER', 100014);