CREATE TABLE IF NOT EXISTS category
(
    category_id identity,
    category_name varchar(100) not null
);
CREATE TABLE IF NOT EXISTS author
(
    author_id  identity,
    first_name varchar(100) not null,
    last_name  varchar(100) not null,
    email      varchar(50)  not null
);
CREATE TABLE IF NOT EXISTS publisher
(

  publisher_id identity,
  publisher_name varchar(100),
  address varchar(150),
  phone_number varchar(15),
  email varchar(50)
);

CREATE TABLE IF NOT EXISTS book
(
    book_id          identity,
    title            varchar(200),
    description      clob,
    number_of_pages  smallint,
    isbn             varchar(50),
    picture          blob,
    discount         decimal(2, 2)  default 0,
    publication_date date,
    edition          tinyint,
    city             varchar(100),
    country          varchar(100),
    rating           decimal(1, 1),
    price            decimal(10, 2) default 0,
    id_category      bigint,
    id_publisher     bigint,
    foreign key (id_category) references category (category_id),
    foreign key (id_publisher) references publisher (publisher_id)
);

CREATE TABLE IF NOT EXISTS user
(
    user_id       identity,
    username      varchar(250) not null,
    email         varchar(100) not null,
    date_of_birth date,
    password      varchar(255) not null,
    first_name    varchar(100) not null,
    last_name     varchar(100) not null,
    phone_number  varchar(15)  not null,
    address       varchar(150) not null,
    postcode      varchar(20)  not null,
    city          varchar(100),
    country       varchar(100)
);

CREATE TABLE IF NOT EXISTS order
(
    order_id          identity,
    order_processed   boolean,
    order_status_sent boolean,
    order_date        timestamp,
    id_user           bigint,
    foreign key (id_user) references user (user_id)
);

