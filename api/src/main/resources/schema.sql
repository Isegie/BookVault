CREATE TABLE IF NOT EXISTS category
(
    category_id identity,
    category_name varchar(100)
);
CREATE TABLE IF NOT EXISTS author
(
  author_id identity,
  first_name varchar(100),
  last_name varchar(100),
  email varchar(50)
);
CREATE TABLE IF NOT EXISTS publisher
(
  publisher_id identity,
  publisher_name varchar(100),
  address varchar(150),
  phone_number varchar(15),
  email varchar(50)
);