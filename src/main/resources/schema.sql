DROP TABLE IF EXISTS SPRING_AI_CHAT_MEMORY;

CREATE TABLE SPRING_AI_CHAT_MEMORY (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    conversation_id VARCHAR(255) NOT NULL,
    content CLOB,
    type VARCHAR(100) NOT NULL, -- Generic type column safely accepts modern tokens
    timestamp TIMESTAMP NOT NULL,
    sequence_id INT NOT NULL
);
