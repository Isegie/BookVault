alter table book
    add column format varchar(50) not null;

alter table book
    add column language varchar(50) not null;

alter table book
    add column price numeric(10, 2) default 0;
