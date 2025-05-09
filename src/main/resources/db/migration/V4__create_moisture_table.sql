CREATE TABLE moisture (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    temperature REAL,
    humidity REAL,
    moisture REAL,
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);