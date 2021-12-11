CREATE TABLE restaurants (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name STRING
);

CREATE TABLE reviews (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    restaurant UUID NOT NULL REFERENCES restaurants (id) ON DELETE CASCADE ON UPDATE CASCADE,
    cuisines STRING[],
    quality INT NOT NULL,
    value INT NOT NULL
);