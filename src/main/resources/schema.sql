DROP TABLE IF EXISTS exchange_rate;
DROP TABLE IF EXISTS users;

CREATE TABLE exchange_rate (
  id uuid DEFAULT random_uuid() PRIMARY KEY,
  code VARCHAR(10) NOT NULL,
  description VARCHAR(250) NOT NULL,
  amount NUMERIC(10,4) NOT NULL
);

CREATE TABLE users (
  id uuid DEFAULT random_uuid() PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(250) NOT NULL,
  rol  VARCHAR(200) not null
);