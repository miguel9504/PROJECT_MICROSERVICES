-- -----------------------------------------------------
-- BaseDatos.sql
-- Language: English (Standard Professional Naming)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- SERVICE 1: CLIENTS (Tables: person, client)
-- Strategy: JOINED Inheritance
-- -----------------------------------------------------

CREATE TABLE person (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        identification VARCHAR(20) NOT NULL UNIQUE, -- DNI/Passport
                        name VARCHAR(100) NOT NULL,
                        gender VARCHAR(20),
                        age INT,
                        address VARCHAR(200),
                        phone VARCHAR(20)
);

CREATE TABLE client (
                        id BIGINT PRIMARY KEY, -- Same PK as person (JOINED strategy)
                        client_id VARCHAR(50) NOT NULL UNIQUE, -- The business ID required by the prompt
                        password VARCHAR(255) NOT NULL,
                        status BOOLEAN NOT NULL DEFAULT TRUE,
                        CONSTRAINT fk_client_person FOREIGN KEY (id) REFERENCES person(id)
);

-- -----------------------------------------------------
-- SERVICE 2: ACCOUNTS (Tables: account, movement)
-- -----------------------------------------------------

CREATE TABLE account (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         account_number VARCHAR(20) NOT NULL UNIQUE,
                         account_type VARCHAR(20) NOT NULL, -- 'SAVINGS', 'CHECKING'
                         balance DECIMAL(15, 2) NOT NULL,
                         status BOOLEAN NOT NULL DEFAULT TRUE,
                         client_identification VARCHAR(20) NOT NULL, -- Logical Link to Client Service (Not a FK constraint between DBs)
                         client_name VARCHAR(100) -- Denormalized for Reporting performance
);

CREATE TABLE movement (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          date DATETIME NOT NULL,
                          movement_type VARCHAR(50) NOT NULL, -- 'DEPOSIT', 'WITHDRAWAL'
                          value DECIMAL(15, 2) NOT NULL,
                          balance DECIMAL(15, 2) NOT NULL, -- Balance after transaction
                          account_id BIGINT NOT NULL,
                          CONSTRAINT fk_movement_account FOREIGN KEY (account_id) REFERENCES account(id)
);

-- -----------------------------------------------------
-- SEED DATA (Translated to match Schema)
-- -----------------------------------------------------

-- Persons
INSERT INTO person (identification, name, gender, age, address, phone) VALUES
                                                                           ('111111', 'Jose Lema', 'Male', 30, 'Otavalo sn y principal', '098254785'),
                                                                           ('222222', 'Marianela Montalvo', 'Female', 28, 'Amazonas y NNUU', '097548965'),
                                                                           ('333333', 'Juan Osorio', 'Male', 35, '13 junio y Equinoccial', '098874587');

-- Clients (Linked by ID)
INSERT INTO client (id, client_id, password, status) VALUES
                                                         (1, 'jose.lema', '1234', true),
                                                         (2, 'marianela.montalvo', '5678', true),
                                                         (3, 'juan.osorio', '1245', true);

-- Accounts
INSERT INTO account (account_number, account_type, balance, status, client_identification, client_name) VALUES
                                                                                                                    ('478758', 'SAVINGS', 2000.00, true, '111111', 'Jose Lema'),
                                                                                                                    ('225487', 'CHECKING', 100.00, true, '222222', 'Marianela Montalvo'),
                                                                                                                    ('495878', 'SAVINGS', 0.00, true, '333333', 'Juan Osorio'),
                                                                                                                    ('496825', 'SAVINGS', 540.00, true, '222222', 'Marianela Montalvo'),
                                                                                                                    ('585545', 'CHECKING', 1000.00, true, '111111', 'Jose Lema');

-- Movements
INSERT INTO movement (date, movement_type, value, balance, account_id) VALUES
    (NOW(), 'WITHDRAWAL', -575.00, 1425.00, 1);