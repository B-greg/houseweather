CREATE TABLE weather(
    id SERIAL PRIMARY KEY ,
    temperature  float,
    humidity float,
    dateTime  timestamp NOT NULL DEFAULT NOW()
);