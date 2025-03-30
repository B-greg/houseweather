CREATE TABLE moisture(
    id SERIAL PRIMARY KEY ,
    temperature float,
    humidity float,
    moisture float,
    CreatedDate  timestamp NOT NULL DEFAULT NOW()
);