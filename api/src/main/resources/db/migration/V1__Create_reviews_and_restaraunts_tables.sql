CREATE TABLE restaraunts (
    id INT PRIMARY KEY,
    name STRING
);

CREATE TABLE reviews (
    id UUID PRIMARY KEY,
    restaraunt INT NOT NULL REFERENCES restaraunts (id) ON DELETE CASCADE ON UPDATE CASCADE,
    cuisines STRING[],
    quality INT NOT NULL,
    value INT NOT NULL
);