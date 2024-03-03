create table event_store(
  id int not null AUTO_INCREMENT,
  payload clob not null,
  header_value clob not null,
  org_queue varchar(255) not null,
  org_exchange varchar(255) not null,
  org_routing_key varchar(255) not null,
  createdAt date,
  updatedAt date,
  primary key (id)
);
