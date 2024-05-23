-- Insert initial employers
INSERT INTO employer (name, address) VALUES ('Company A', '123 Main St');
INSERT INTO employer (name, address) VALUES ('Company B', '456 Oak Ave');
INSERT INTO employer (name, address) VALUES ('Company C', '789 Pine Rd');

-- Insert initial customers
INSERT INTO customer (name, email, age) VALUES ('John Doe', 'john.doe@example.com', 30);
INSERT INTO customer (name, email, age) VALUES ('Jane Smith', 'jane.smith@example.com', 25);
INSERT INTO customer (name, email, age) VALUES ('Alice Johnson', 'alice.johnson@example.com', 28);

-- Insert initial customer-employer relationships
INSERT INTO customer_employer (customer_id, employer_id) VALUES (
                                                                    (SELECT id FROM customer WHERE email = 'john.doe@example.com'),
                                                                    (SELECT id FROM employer WHERE name = 'Company A')
                                                                );
INSERT INTO customer_employer (customer_id, employer_id) VALUES (
                                                                    (SELECT id FROM customer WHERE email = 'jane.smith@example.com'),
                                                                    (SELECT id FROM employer WHERE name = 'Company B')
                                                                );
INSERT INTO customer_employer (customer_id, employer_id) VALUES (
                                                                    (SELECT id FROM customer WHERE email = 'alice.johnson@example.com'),
                                                                    (SELECT id FROM employer WHERE name = 'Company C')
                                                                );
INSERT INTO customer_employer (customer_id, employer_id) VALUES (
                                                                    (SELECT id FROM customer WHERE email = 'john.doe@example.com'),
                                                                    (SELECT id FROM employer WHERE name = 'Company B')
                                                                );
INSERT INTO customer_employer (customer_id, employer_id) VALUES (
                                                                    (SELECT id FROM customer WHERE email = 'alice.johnson@example.com'),
                                                                    (SELECT id FROM employer WHERE name = 'Company A')
                                                                );

-- Insert initial accounts
INSERT INTO account (number, currency, balance, customer_id) VALUES (
                                                                        'acc1', 'USD', 1000.0,
                                                                        (SELECT id FROM customer WHERE email = 'john.doe@example.com')
                                                                    );
INSERT INTO account (number, currency, balance, customer_id) VALUES (
                                                                        'acc2', 'EUR', 2000.0,
                                                                        (SELECT id FROM customer WHERE email = 'jane.smith@example.com')
                                                                    );
INSERT INTO account (number, currency, balance, customer_id) VALUES (
                                                                        'acc3', 'GBP', 1500.0,
                                                                        (SELECT id FROM customer WHERE email = 'alice.johnson@example.com')
                                                                    );
INSERT INTO account (number, currency, balance, customer_id) VALUES (
                                                                        'acc4', 'USD', 1200.0,
                                                                        (SELECT id FROM customer WHERE email = 'john.doe@example.com')
                                                                    );
INSERT INTO account (number, currency, balance, customer_id) VALUES (
                                                                        'acc5', 'EUR', 800.0,
                                                                        (SELECT id FROM customer WHERE email = 'alice.johnson@example.com')
                                                                    );
