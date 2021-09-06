create sequence products_id_generator
    increment by 1;

alter sequence products_id_generator
    owner to postgres;

create table products
(
    id bigint not null default nextval('products_id_generator')
        constraint products_id primary key,
    price double precision,
    is_deleted boolean,
    article bigint not null,
    name varchar (40) not null
);

alter table products
        owner to postgres;

create sequence orders_id_generator
    increment by 1;

alter sequence orders_id_generator
    owner to postgres;

create table orders
(
    id bigint not null default nextval('orders_id_generator')
        constraint orders_id primary key,
    order_number integer default md5('date_of_creation') not null,
    e_mail varchar(40) not null,
    date_of_creation timestamp without time zone not null,
    list_of_products bigint not null references products(id)
);

alter table orders
        owner to postgres;

create sequence orders_products_id_generator
    increment by 1;

alter sequence orders_products_id_generator
    owner to postgres;

create table orders_products
(
    id bigint not null default nextval('orders_products_id_generator')
        constraint orders_products_id primary key,
    orders_id bigint not null references orders (id),
    products_id bigint not null references products (id)
);

alter table orders_products
    owner to postgres;
