CREATE TABLE users(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(50) NOT NULL UNIQUE,PASSWORD  VARCHAR(50) NOT NULL);

INSERT INTO users(NAME,PASSWORD) VALUE('senthamil','pass@123');

SELECT id,NAME,PASSWORD FROM users;