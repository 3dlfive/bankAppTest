-- Create table for employers
CREATE TABLE employer (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          address VARCHAR(255) NOT NULL
);

-- Create table for customers
CREATE TABLE customer (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          age INTEGER NOT NULL,
                          password  VARCHAR(255) NOT NULL,
                          phone  VARCHAR(255) NOT NULL
);

-- Create table for accounts
CREATE TABLE account (
                         id BIGSERIAL PRIMARY KEY,
                         number VARCHAR(255) NOT NULL,
                         currency VARCHAR(255) NOT NULL,
                         balance DOUBLE PRECISION NOT NULL,
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
--create tables
BEGIN;


ALTER TABLE customer ADD COLUMN     created_date      TIMESTAMP  NULL;
ALTER TABLE customer ADD COLUMN     last_modified_date      TIMESTAMP  NULL;
ALTER TABLE account ADD COLUMN     created_date      TIMESTAMP  NULL;
ALTER TABLE account ADD COLUMN     last_modified_date      TIMESTAMP  NULL;
ALTER TABLE employer ADD COLUMN     created_date      TIMESTAMP  NULL;
ALTER TABLE employer ADD COLUMN     last_modified_date      TIMESTAMP  NULL;

COMMIT;
