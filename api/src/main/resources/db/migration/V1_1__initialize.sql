create table if not exists category
(
    category_id   bigint generated always as identity,
    category_name varchar(100) not null,
    primary key (category_id)
);

alter table category
    owner to postgres;

create table if not exists author
(
    author_id  bigint generated always as identity,
    first_name varchar(100) not null,
    last_name  varchar(100) not null,
    email      varchar(50),
    primary key (author_id)
);

alter table author
    owner to postgres;

create table if not exists publisher
(
    publisher_id   bigint generated always as identity,
    publisher_name varchar(100) not null,
    address        varchar(150) not null,
    phone_number   varchar(15)  not null,
    email          varchar(50)  not null,
    primary key (publisher_id)
);

alter table publisher
    owner to postgres;

create table if not exists book
(
    book_id          bigint generated always as identity,
    title            varchar(200) not null,
    description      text         not null,
    number_of_pages  integer     not null,
    isbn             varchar(50) unique,
    picture          bytea,
    discount         numeric(2, 2)  default 0,
    publication_date date         not null,
    edition          integer       default 0,
    city             varchar(100) not null,
    country          varchar(100) not null,
    rating           numeric(10, 2) default 0,
    id_category      bigint,
    id_publisher     bigint,
    primary key (book_id),
    constraint fk_category
        foreign key (id_category)
            references category (category_id)
            on delete set null,
    constraint fk_publisher
        foreign key (id_publisher)
            references publisher (publisher_id)
            on delete set null
);

alter table book
    owner to postgres;

create table if not exists users
(
    user_id       bigint generated always as identity,
    username      varchar(250) not null,
    email         varchar(100) not null,
    date_of_birth date         not null,
    user_password varchar(255) not null,
    first_name    varchar(100) not null,
    last_name     varchar(100) not null,
    phone_number  varchar(15)  not null,
    address       varchar(150) not null,
    postcode      varchar(20)  not null,
    city          varchar(100) not null,
    country       varchar(100) not null,
    primary key (user_id)
);

alter table users
    owner to postgres;

create table if not exists orders
(
    order_id          bigint generated always as identity,
    order_processed   boolean,
    order_status_sent boolean,
    order_date        timestamp,
    id_user           bigint,
    primary key (order_id),
    constraint fk_user
        foreign key (id_user)
            references users (user_id)
            on delete set null
);

alter table orders
    owner to postgres;

create table if not exists wishlist
(
    wishlist_id bigint generated always as identity,
    id_user     bigint,
    primary key (wishlist_id),
    constraint fk_user
        foreign key (id_user)
            references users (user_id)
            on delete set null
);

alter table wishlist
    owner to postgres;

create table if not exists wishlist_group
(
    wishlist_group_id bigint generated always as identity,
    id_wishlist       bigint,
    primary key (wishlist_group_id),
    constraint fk_wishlist
        foreign key (id_wishlist)
            references wishlist (wishlist_id)
            on delete set null
);

alter table wishlist_group
    owner to postgres;


create table if not exists reviews
(
    review_id      bigint generated always as identity,
    book_id        bigint  not null,
    user_id        bigint  not null,
    content        text not null,
    rating         int       default 0,
    published_date timestamp default current_timestamp,
    primary key (review_id),
    constraint fk_book foreign key (book_id) references book (book_id) on delete set null ,
    constraint fk_user foreign key (user_id) references users (user_id) on delete set null
);

create table if not exists basket
(
    basket_id   bigint generated always as identity,
    total_price numeric(10, 2) default 0,
    id_user     bigint,
    id_book     bigint,
    primary key (basket_id),
    constraint fk_user
        foreign key (id_user)
            references users (user_id)
            on delete set null,
    constraint fk_book
        foreign key (id_book)
            references book (book_id)
            on delete set null
);

alter table basket
    owner to postgres;

create table if not exists book_author
(
    id_book   bigint references book (book_id),
    id_author bigint references author (author_id)
);

alter table book_author
    owner to postgres;

create table if not exists book_order
(
    id_book  bigint references book (book_id),
    id_order bigint references orders (order_id),
    amount   integer default 0
);

alter table book_order
    owner to postgres;

create table if not exists book_basket
(
    id_book   bigint references book (book_id),
    id_basket bigint references basket (basket_id)
);

alter table book_basket
    owner to postgres;

create table if not exists book_wishlist
(
    id_book     bigint references book (book_id),
    id_wishlist bigint references wishlist (wishlist_id)
);

alter table book_wishlist
    owner to postgres;
