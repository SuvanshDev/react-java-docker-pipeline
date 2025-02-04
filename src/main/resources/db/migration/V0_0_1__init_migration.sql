CREATE TABLE todo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status ENUM('pending', 'completed', 'archived') NOT NULL DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO todo (title, description, status)
VALUES
    ('Finish Assignment', 'Complete the assignment on database management systems.', 'pending'),
    ('Grocery Shopping', 'Buy milk, eggs, and bread from the supermarket.', 'completed');
