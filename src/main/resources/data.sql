CREATE TABLE note (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(100) NOT NULL UNIQUE,
                      content TEXT NOT NULL
);


INSERT INTO note (title, content) VALUES ('First', 'Some content');
