CREATE TABLE countries
(
    id        int primary key AUTO_INCREMENT,
    name      varchar(255) not null unique,
    code      varchar(2) unique,
    continent int
);

CREATE TABLE continents
(
    id   int primary key AUTO_INCREMENT,
    name varchar(255) not null unique
);

CREATE TABLE cities
(
    id        int primary key AUTO_INCREMENT,
    name      varchar(255) not null,
    country   int,
    capital   BOOLEAN,
    latitude  DECIMAL(11, 8),
    longitude DECIMAL(11, 8),
    UNIQUE (name, country)
);

ALTER TABLE countries
    ADD CONSTRAINT fk_continent FOREIGN KEY (continent)
        REFERENCES continents (id);

ALTER TABLE cities
    ADD CONSTRAINT fk_country FOREIGN KEY (country)
        REFERENCES countries (id);