-- Create table for employers
CREATE TABLE employer (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          address VARCHAR(255) NOT NULL
);

-- Create table for customers
CREATE TABLE customer (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          age INTEGER NOT NULL
);

-- Create table for accounts
CREATE TABLE account (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         number VARCHAR(255) NOT NULL,
                         currency VARCHAR(255) NOT NULL,
                         balance DOUBLE NOT NULL,
                         customer_id BIGINT NOT NULL,
                         CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

-- Create table for customer-employer relationships
CREATE TABLE customer_employer (
                                   customer_id BIGINT NOT NULL,
                                   employer_id BIGINT NOT NULL,
                                   PRIMARY KEY (customer_id, employer_id),
                                   CONSTRAINT fk_customer_employer_customer FOREIGN KEY (customer_id) REFERENCES customer(id),
                                   CONSTRAINT fk_customer_employer_employer FOREIGN KEY (employer_id) REFERENCES employer(id)
);
