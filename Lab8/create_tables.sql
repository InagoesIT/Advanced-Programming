CREATE TABLE countries (
    id int primary key,
    name varchar(255) not null,
    code int,
    continent int
);
CREATE TABLE continents (
    id int primary key,
    name varchar(255) not null
);
alter table countries ADD CONSTRAINT fk_continent FOREIGN KEY(continent)
    references continents(id);
