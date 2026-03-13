DROP DATABASE IF EXISTS socialnetworkproject;
CREATE DATABASE socialnetworkproject;
USE socialnetworkproject;
CREATE TABLE users (
    id INT auto_increment PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) unique NOT NULL,
    senha VARCHAR(255) NOT NULL,
    statusUser VARCHAR(20) NOT NULL
);

CREATE TABLE posts (
    id INT auto_increment PRIMARY KEY,
    postContent VARCHAR(1000) NOT NULL,
    datePost TIMESTAMP NOT NULL,
    user_id INT NOT NULL, 
    FOREIGN KEY (user_id) REFERENCES users(id) 
);


