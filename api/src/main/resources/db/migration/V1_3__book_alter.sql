alter table book
    add column format varchar(50) not null;

alter table book
    add column language varchar(50) not null;

alter table book
    add column price numeric(10, 2) default 0;

create table if not exists reviews
(
    review_id      bigint generated always as identity,
    book_id        int  not null,
    user_id        int  not null,
    content        text not null,
    rating         int       default 0,
    published_date timestamp default current_timestamp,
    primary key (review_id),
    foreign key (book_id) references book (book_id) on delete cascade,
    foreign key (user_id) references users (user_id) on delete cascade
);
