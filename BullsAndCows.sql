DROP DATABASE IF EXISTS BullsCows;
CREATE DATABASE BullsCows;

USE BullsCows;

CREATE TABLE game(
	gameId INT PRIMARY KEY AUTO_INCREMENT,
	startTime DATETIME,
	numberOfGuesses INT, 
	answer VARCHAR(4),
	won BOOLEAN DEFAULT false
);

CREATE TABLE round(
	roundId INT PRIMARY KEY AUTO_INCREMENT,
    guess VARCHAR(4) NOT NULL,
	timestamp DATETIME,
	result VARCHAR(7), 
	gameId INT NOT NULL,
	FOREIGN KEY (gameId) REFERENCES game(gameId)
);

DROP DATABASE IF EXISTS BullsCowsTest;
CREATE DATABASE BullsCowsTest;

USE BullsCowsTest;

CREATE TABLE game(
	gameId INT PRIMARY KEY AUTO_INCREMENT,
	startTime DATETIME,
	numberOfGuesses INT, 
	answer VARCHAR(4),
	won BOOLEAN DEFAULT false
);

CREATE TABLE round(
	roundId INT PRIMARY KEY AUTO_INCREMENT,
    guess VARCHAR(4) NOT NULL,
	timestamp DATETIME,
	result VARCHAR(7), 
	gameId INT NOT NULL,
	FOREIGN KEY (gameId) REFERENCES game(gameId)
);

SELECT * FROM game;

