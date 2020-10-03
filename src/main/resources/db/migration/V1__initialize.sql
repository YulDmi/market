create table customers (
    id                      bigserial primary key ,
    name                    varchar(255) not null
);

create table products (
    id     bigserial primary key ,
    name   varchar (255) not null,
    cost  int not null

);
create table orders (
    id          bigserial primary key,
    customer_id varchar(255),
    price   int
);

create table order_items (
    id          bigserial primary key ,
    product_id  bigint references products(id),
    order_id    bigint references orders(id),
    price       int,
    price_per_product int,
    quantity    int
);

insert into customers (name)
values
('Tom'),
('John'),
('Nick');


insert into products (name, cost)
values
('bread', 39),
('vine1', 879),
('vine2', 1279),
('vine3', 979),
('vine4', 1354),
('vine5', 1105),
('vine6', 1200),
('cheese1', 570),
('cheese2', 490),
('cheese3', 879),
('cheese4', 970),
('cheese5', 1354),
('meet1', 230),
('meet2', 290),
('meet3', 530),
('meet4', 470),
('Coca-Cola 0.5', 39),
('Coca-Cola 0.9', 46),
('Coca-Cola 1.5', 90),
('Coca-Cola 2.0', 190),
('meet1', 230),
('meet2', 290),
('meet3', 530),
('meet4', 470),
('Coca-Cola 0.5', 39),
('Coca-Cola 0.9', 46),
('Coca-Cola 1.5', 90),
('Coca-Cola 2.0', 190);
