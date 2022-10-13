create table mac_user(
    id bigserial primary key,
    login varchar(20),
    password varchar(20),
    status boolean
);

create table food(
    name varchar(30),
    id bigserial primary key,
    quantity int,
    price int
);

create table mac_order(
    id bigserial primary key,
    food_id bigint,
     food_name varchar(30),
    foreign key(food_id) references food(id)
)