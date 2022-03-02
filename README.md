# BullsAndCows

This is a REST Web Service, built with Spring MVC.
It doesn't have a UI, I tested it with using Postman.
It reads data from a MySQL Database, added in the files.
To make it work, you need to run the BullsAndCows.sql to build the database.
And add your password to the src/main(test)/resources/application.properies file.

How it works?
You can guess a 4 digit number, submitting a JSON with an HTTP Reqest,
the program will tell you if your guess was or wasn't right.
