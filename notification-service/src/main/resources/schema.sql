create table users(
  id int not null AUTO_INCREMENT,
  username varchar(100) not null,
  firstName varchar(100) not null,
  lastName varchar(100) not null,
  userRole varchar(100) not null,
  createdAt date,
  updatedAt date,
  primary key (id)
);
INSERT INTO users (username, firstName, lastName, userRole, createdAt, updatedAt)  VALUES('paul_pop', 'paulous', 'cool', 'admin', '2023-06-25','2023-06-25');
INSERT INTO users (username, firstName, lastName, userRole, createdAt, updatedAt)  VALUES('kaySlow', 'Kay', 'Slow', 'admin', '2023-06-25','2023-06-25');
INSERT INTO users (username, firstName, lastName, userRole, createdAt, updatedAt)  VALUES('ghostNinja', 'Ghost', 'Ninja', 'user', '2023-06-25','2023-06-25');
INSERT INTO users (username, firstName, lastName, userRole, createdAt, updatedAt)  VALUES('fastBean', 'Fast', 'Bean', 'user', '2023-06-25','2023-06-25');
