CREATE TABLE books(
    bookID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL
);

ALTER Table Books ADD COLUMN IMAGEURL VARCHAR(255) NULL;