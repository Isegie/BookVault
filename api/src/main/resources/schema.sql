CREATE TABLE IF NOT EXISTS category
(
    category_id   identity,
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

    publisher_id   identity,
    publisher_name varchar(100),
    address        varchar(150),
    phone_number   varchar(15),
    email          varchar(50)
);

CREATE TABLE IF NOT EXISTS book
(
    book_id          identity,
    title            varchar(200)       not null,
    description      clob               not null,
    number_of_pages  smallint           not null,
    isbn             varchar(50) unique not null,
    picture          blob               not null,
    discount         decimal(2, 2)  default 0,
    publication_date date               not null,
    edition          tinyint            not null,
    city             varchar(100)       not null,
    country          varchar(100)       not null,
    rating           decimal(1, 1),
    price            decimal(10, 2) default 0,
    id_category      bigint,
    id_publisher     bigint,
    foreign key (id_category) references category (category_id),
    foreign key (id_publisher) references publisher (publisher_id)
);

CREATE TABLE IF NOT EXISTS users
(
    user_id       identity,
    username      varchar(250) not null,
    email         varchar(100) not null,
    date_of_birth date,
    user_password varchar(255) not null,
    first_name    varchar(100) not null,
    last_name     varchar(100) not null,
    phone_number  varchar(15)  not null,
    address       varchar(150) not null,
    postcode      varchar(20)  not null,
    city          varchar(100),
    country       varchar(100)
);

CREATE TABLE IF NOT EXISTS orders
(
    order_id          identity,
    order_processed   boolean,
    order_status_sent boolean,
    order_date        timestamp,
    id_user           bigint,
    foreign key (id_user) references users (user_id)
);

CREATE TABLE IF NOT EXISTS wishlist
(
    wishlist_id identity,
    id_user     bigint,
    foreign key (id_user) references users (user_id)
);

CREATE TABLE IF NOT EXISTS wishlist_group
(
    wishlist_group_id identity,
    id_wishlist       bigint,
    foreign key (id_wishlist) references wishlist (wishlist_id)
);

CREATE TABLE IF NOT EXISTS basket
(
    basket_id   identity,
    total_price decimal(10, 2) default 0,
    id_user     bigint,
    id_book     bigint,
    foreign key (id_user) references users (user_id),
    foreign key (id_book) references book (book_id)
);

CREATE TABLE IF NOT EXISTS book_author
(
    id_book   bigint,
    id_author bigint,
    foreign key (id_book) references book (book_id),
    foreign key (id_author) references author (author_id)
);

CREATE TABLE IF NOT EXISTS book_order
(
    id_book  bigint,
    id_order bigint,
    amount   tinyint,
    foreign key (id_book) references book (book_id),
    foreign key (id_order) references orders (order_id)
);

CREATE TABLE IF NOT EXISTS book_basket
(
    id_book   bigint,
    id_basket bigint,
    foreign key (id_book) references book (book_id),
    foreign key (id_basket) references basket (basket_id)
);

CREATE TABLE IF NOT EXISTS book_wishlist
(
    id_book     bigint,
    id_wishlist bigint,
    foreign key (id_book) references book (book_id),
    foreign key (id_wishlist) references wishlist (wishlist_id)
);


