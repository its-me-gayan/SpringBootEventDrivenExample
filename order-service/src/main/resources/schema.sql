create table order_(
  id int not null AUTO_INCREMENT,
  customerId int not null AUTO_INCREMENT,
  totalAmount double not null,
  itemCount int not null,
  status varchar not null,
  createdAt date,
  updatedAt date,
  primary key (id)
);

create table order_item(
  id int not null AUTO_INCREMENT,
  order_id int not null,
  itemId int not null,
  quantity int not null,
  createdAt date,
  updatedAt date,
  primary key (id),
  foreign key (order_id) references order_(id)
);