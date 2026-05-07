create database javabook;
create user 'scott'@'localhost' identified by 'tiger';

grant select, insert, update, delete, create, create view, drop,
 execute, references on javabook.* to 'scott'@'localhost';
FLUSH PRIVILEGES;

use javabook;
drop table if exists Events;

CREATE TABLE Events (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    EventDate DATE NOT NULL,
    Name VARCHAR(255) NOT NULL,
    StartTime TIME NOT NULL,
    EndTime TIME NOT NULL,
    Category VARCHAR(100) NOT NULL,
    Notes TEXT
);