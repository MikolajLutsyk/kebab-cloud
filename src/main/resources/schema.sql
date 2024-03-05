create table if not exists Kebab_Order (
    id bigint generated by default as identity primary key,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_Country varchar(30) not null,
    delivery_postal_code varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
    );
create table if not exists Kebab (
    id bigint generated by default as identity primary key,
    name varchar(50) not null,
    kebab_order bigint not null,
    kebab_order_key bigint not null,
    created_at timestamp not null
    );
create table if not exists Ingredient_Ref (
    ingredient varchar(4) primary key,
    kebab bigint not null,
    kebab_key bigint not null
    );
create table if not exists Ingredient (
    id varchar(4) primary key,
    name varchar(25) not null,
    type varchar(10) not null
    );
alter table Kebab
    add foreign key (kebab_order) references Kebab_Order(id);
alter table Ingredient_Ref
    add foreign key (ingredient) references Ingredient(id);