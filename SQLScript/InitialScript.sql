create database pet_store;
use pet_store;

create table user(
	id int auto_increment primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255),
    budget_amount decimal(10,2) not null,
    budget_currency varchar(10) not null
);

create table pet(
	id int auto_increment primary key,
    name varchar(255) not null,
    type varchar(20) not null,
    description text,
    birth_date date not null,
    price_amount decimal(10,2) not null,
    price_currency varchar(10) not null,
    owner_id int,
    constraint fk_pet_owner foreign key (owner_id) references user(id)
);

create table cat(
	id int primary key,
    constraint fk_cat_pet foreign key (id) references pet(id)
);

create table dog(
	id int primary key,
    rating int not null,
    constraint fk_dog_pet foreign key(id) references pet(id)
);

create table purchase_history(
	id int auto_increment primary key,
    execution_date date not null,
    successful_buyers int not null,
    failed_buyers int not null
)