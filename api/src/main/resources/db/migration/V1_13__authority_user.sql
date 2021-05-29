create table if not exists authority
(
    id   bigint generated always as identity,
    name varchar(100) unique not null,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS user_authority
(
    user_id   bigint references users (user_id),
    authority_id bigint references authority (id)
);