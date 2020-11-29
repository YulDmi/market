create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

create table details (
  id                    bigserial primary key,
  user_id               bigint references users(id),
  firstname             varchar(30),
  lastname              varchar(30),
  address              varchar(80),
  phone                 varchar(50),
  year                 int,
  sex                   varchar(50)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('SOMETHING');


insert into users (username, password, email)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');



insert into users_roles
(user_id, role_id) values
(1, 1),
(2, 1), (2, 2);

insert into details (user_id, firstname, lastname, address, phone, year, sex)
values
(1, ' ', ' ', ' ', ' ', 1900, 'male'),
(2, ' ', ' ',' ', ' ', 1999, 'female');

create table category (
    id                      bigserial primary key,
    title                  varchar(255)
);

create table products (
    id                      bigserial primary key,
    name                  varchar(255),
    cost                   int,
    category_id            bigint references category(id)
);

create table orders (
    id                      bigserial primary key,
    user_id                 bigint references users(id),
    price                   int,
    address                 varchar(1000)
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint references products(id),
    order_id                bigint references orders(id),
    price                   int,
    price_per_product       int,
    quantity                int
);


insert into category (title)
values
('Хлеб'),
('Выпечка');

insert into products (name, cost, category_id)
values
('Bread1', 1,1),
('Bread2', 2,1),
('Bread3', 3,1),
('Bread4', 32,2),
('Bread5', 42,2),
('Bread6', 52,1),
('Bread7', 32,1),
('Bread8', 42,1),
('Bread9', 552,2),
('Bread10', 32,2),
('Bread11', 122,2),
('Bread12', 312,2),
('Bread13', 22,2),
('Bread14', 432,2),
('Bread15', 532,1),
('Bread16', 542,1),
('Bread17', 232,1),
('Bread18', 22,1),
('Bread19', 22,1),
('Bread20', 22,2),
('Bread21', 22,2),
('Bread22', 22,2),
('Bread23', 22,1),
('Bread24', 22,1),
('Bread25', 22,1)
