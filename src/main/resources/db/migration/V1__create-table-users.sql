CREATE TABLE IF NOT EXISTS tb_users(
    id bigint PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    login varchar UNIQUE NOT NULL,
    password varchar NOT NULL,
    role varchar NOT NULL
)