insert into products (price, is_deleted, article, name)
    values (350, false, 5374821, 'Xiaomi note 9');

insert into products (price, is_deleted, article, name)
    values (300, false, 4365328, 'Huawei 30 lite');

insert into products (price, is_deleted, article, name)
    values (400, false, 1293450, 'Google pixel 4a');

insert into products (price, is_deleted, article, name)
    values (1200, false, 0945672, 'Iphone 12 Pro Max');

insert into products (price, is_deleted, article, name)
    values (1300, false, 6984539, 'Samsung Galaxy Z Fold 3');

insert into products (price, is_deleted, article, name)
    values (250, false, 4365328, 'Samsung A12');

insert into orders (order_number, e_mail, date_of_creation)
    values (md5(localtimestamp), 'ivan123@mail.ru', localtimestamp);

insert into orders (order_number, e_mail, date_of_creation)
    values (md5(localtimestamp), 'vna48@mail.ru', localtimestamp);

insert into orders (order_number, e_mail, date_of_creation)
    values (md5(localtimestamp), 'destroyer@yandex.ru', localtimestamp);

insert into orders (order_number, e_mail, date_of_creation)
    values (md5(localtimestamp), 'cybergirl2000@gmail.com', localtimestamp);

insert into orders (order_number, e_mail, date_of_creation)
    values (md5(localtimestamp), 'nimbus2001@mail.ru', localtimestamp);

insert into orders (order_number, e_mail, date_of_creation)
    values (md5(localtimestamp), 'khalisi1994@rambler.ru', localtimestamp);

insert into orders_products (orders_id, products_id)
    values (3, 1);

insert into orders_products (orders_id, products_id)
    values (4, 3);

insert into orders_products (orders_id, products_id)
    values (5, 5);

insert into orders_products (orders_id, products_id)
    values (6, 1);

insert into orders_products (orders_id, products_id)
    values (7, 2);

insert into orders_products (orders_id, products_id)
    values (7, 5);

