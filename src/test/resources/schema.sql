CREATE TABLE ship
(
    id       BIGINT             AUTO_INCREMENT,
    name     VARCHAR(50) 		NULL,
    planet   VARCHAR(50) 		NULL,
    shipType VARCHAR(9)  		NULL,
    prodDate DATE        		NULL,
    isUsed   BOOLEAN      		NULL,
    speed    DOUBLE PRECISION   NULL,
    crewSize INTEGER      		NULL,
    rating   DOUBLE PRECISION   NULL,
    PRIMARY KEY (id)
);