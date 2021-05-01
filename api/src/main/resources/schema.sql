CREATE TABLE IF NOT EXISTS category
(
    id identity,
    name varchar(100)
);
CREATE TABLE IF NOT EXISTS author
(
  id identity,
  first_name varchar(100),
  last_name varchar(100),
  email varchar(50)
);
CREATE TABLE IF NOT EXISTS publisher
(
  id identity,
  name varchar(100),
  address varchar(150),
  phone_number varchar(15),
  email varchar(50)
);