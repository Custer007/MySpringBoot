CREATE TABLE ur_user (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(50) NOT NULL,
                            phone VARCHAR(50) NULL,
                            email VARCHAR(100) UNIQUE
);
