CREATE TABLE board (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(255) NOT NULL,
                       content TEXT,
                       attachment_url VARCHAR(500),
                       created_at TIMESTAMP,
                       views INT DEFAULT 0
);