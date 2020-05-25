CREATE USER 'colamooon'@'%' IDENTIFIED WITH mysql_native_password BY 'colamooon@!prod';
CREATE DATABASE colamooon;

GRANT ALL ON colamooon.* TO 'colamooon'@'%';

FLUSH PRIVILEGES;
