CREATE TABLE cards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    card_number BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    card_type VARCHAR(50) NOT NULL,
    CONSTRAINT fk_user
       FOREIGN KEY (user_id)
           REFERENCES users(id)
           ON DELETE CASCADE
);