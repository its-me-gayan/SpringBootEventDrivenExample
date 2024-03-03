create table item(
  id int not null AUTO_INCREMENT,
  itemName varchar(100) not null,
  price double not null,
  itemDescription varchar(100) not null,
  category varchar(100) not null,
  createdAt date,
  updatedAt date,
  primary key (id)
);