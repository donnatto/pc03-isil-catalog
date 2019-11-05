CREATE TABLE country (
    country_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE city (
    city_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    country_id INT,
    FOREIGN KEY(country_id) REFERENCES country(country_id)
)