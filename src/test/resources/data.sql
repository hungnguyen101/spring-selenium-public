DROP TABLE IF EXISTS customer;
CREATE TABLE customer as SELECT * FROM CSVREAD('classpath:tables/user_visa.csv');