drop table if exists person;
drop table if exists projekt;
drop table if exists zeitraum;

CREATE TABLE IF NOT EXISTS zeitraum
(
    id      integer primary key,
    von     DATE,
    bis     DATE
);

CREATE TABLE IF NOT EXISTS projekt
(
    id      integer primary key,
    name    varchar(100),
    beschreibung    varchar(500),
    zeitraum integer references zeitraum(id)
);
CREATE TABLE IF NOT EXISTS person
(
    id      integer primary key,
    name    varchar(200),
    projekt integer references projekt(id)
);
