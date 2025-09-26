DROP TABLE IF EXISTS ship;

CREATE TABLE ship
(
    id       BIGSERIAL,
    name     VARCHAR(50) 		NULL,
    planet   VARCHAR(50) 		NULL,
    shipType VARCHAR(9)  		NULL,
    prodDate date        		NULL,
    isUsed   boolean      		NULL,
    speed    DOUBLE PRECISION   NULL,
    crewSize INTEGER      		NULL,
    rating   DOUBLE PRECISION   NULL,
    PRIMARY KEY (id)
);

insert into ship(name, planet, shipType, prodDate, isUsed, speed, crewSize, rating)
values ('Orion III', 'Mars', 'MERCHANT', '2995-01-01', true, 0.82, 617, 1.31)
     , ('Daedalus', 'Jupiter', 'MERCHANT', '3001-01-01', true, 0.94, 1619, 1.98)
     , ('Eagle Transporter', 'Earth', 'TRANSPORT', '2989-01-01', true, 0.79, 4527, 1.02)
     , ('F-302 Mongoose', 'Neptune', 'MILITARY', '3011-01-01', false, 0.24, 2170, 2.13)
     , ('Proedalus', 'Mercury', 'MILITARY', '3011-01-01', false, 0.64, 128, 5.69);