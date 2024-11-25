CREATE TABLE IF NOT EXISTS tb_tasks(
    id bigint PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    title varchar,
    description varchar,
    user_id bigint,
    FOREIGN KEY (user_id) REFERENCES tb_users(id) ON DELETE CASCADE
);