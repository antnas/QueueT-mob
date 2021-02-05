drop table if exists person;
drop table if exists projekt;
drop table if exists zeitraum;

CREATE TABLE IF NOT EXISTS projekt
(
    id      integer primary key,
    name    varchar(100),
    beschreibung    varchar(500)
);
CREATE TABLE IF NOT EXISTS zeitraum
(
    projekt long primary key references projekt(id),
    von     DATE,
    bis     DATE
);
CREATE TABLE IF NOT EXISTS person
(
    id      integer primary key,
    name    varchar(200),
    projekt integer references projekt(id)
);

insert into projekt values ( 1, 'Projekt B', 'Das ist ein Projekt');
insert into projekt values ( 2, 'Projekt C', 'Noch ein Projekt');
insert into zeitraum values (1,'2021-01-01','2021-01-01');
insert into zeitraum values (2, '2020-01-01', '2020-02-01');
insert into person values (1, 'Max', 1);
insert into person values ( 2, 'Maxine', 2 );
