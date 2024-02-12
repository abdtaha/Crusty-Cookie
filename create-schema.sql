SET FOREIGN_KEY_CHECKS=0;
drop table if exists Pallets;
drop table if exists Orders;
drop table if exists Customers;
drop table if exists Cookies;
drop table if exists Raw_materials;
drop table if exists Recipes;

create table Customers (
name varchar(20),
address varchar(40) not null,
primary key (name));

create table Orders (
orderId int auto_increment,
order_Date date not null,
name varchar(20),
primary key (orderId),
foreign key (name) references Customers(name));

create table Pallets (
palletId int auto_increment,
proDate date,
cookieName varchar(20),
blocked boolean,
orderId int,
delivery_Date date,
primary key (palletId),
foreign key (cookieName) references Cookies(cookieName),
foreign key (orderId) references Orders(orderId));

create table Raw_materials (
ingredientName varchar(30),
storedAmount int,
unit varchar(20),
LdDate date,
LdAmount int,
primary key (ingredientName));

create table Cookies (
cookieName varchar(20),
primary  key (cookieName));

create table Recipes(
    amount int,
    unit varchar(20),
    cookieName varchar(20),
    ingredientName varchar(30),
    primary  key (cookieName, ingredientName),
    foreign key (cookieName) references Cookies(cookieName),
    foreign key (ingredientName) references Raw_materials(ingredientName));
    
    SET FOREIGN_KEY_CHECKS=1;
