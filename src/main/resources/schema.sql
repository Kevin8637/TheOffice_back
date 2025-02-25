CREATE TABLE IF NOT EXISTS User
(
    id       INT AUTO_INCREMENT     NOT NULL PRIMARY KEY,
    email    VARCHAR(255) UNIQUE    NOT NULL,
    username VARCHAR(255)           NOT NULL,
    password VARCHAR(255)           NOT NULL,
    role     ENUM ('user', 'admin') NOT NULL DEFAULT 'user',
    wallet    DECIMAL(10, 2)     NOT NULL DEFAULT 0.00
);

CREATE TABLE IF NOT EXISTS Company
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    sector       VARCHAR(255)       NOT NULL,
    name          VARCHAR(255)       NOT NULL,
    creation_date DATE               NOT NULL,
    id_user       INT,
    FOREIGN KEY (id_user) REFERENCES User (id)
);

CREATE TABLE IF NOT EXISTS Loan
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    loan_amount   DECIMAL(10, 2)     NOT NULL,
    interest_rate DECIMAL(5, 2)      NOT NULL,
    duration      INT                NOT NULL,
    rest DECIMAL(10, 2)     NOT NULL,
    id_user       INT,
    FOREIGN KEY (id_user) REFERENCES User (id)
);

CREATE TABLE IF NOT EXISTS Supplier
(
    id         INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name       VARCHAR(255)       NOT NULL,
    price      DECIMAL(10, 2)     NOT NULL,
    quality    VARCHAR(100)       NOT NULL,
    id_company INT,
    FOrEIGN KEY (id_company) REFERENCES Company (id)
);

CREATE TABLE IF NOT EXISTS Cycle
(
    id           INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    cost         DECIMAL(10, 2)     NOT NULL,
    employees    INT                NOT NULL,
    productivity INT                NOT NULL,
    popularity   INT                NOT NULL,
    step         INT                NOT NULL,
    id_company   INT,
    FOREIGN KEY (id_company) REFERENCES Company (id)
);

CREATE TABLE IF NOT EXISTS Machine
(
    id               INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name             VARCHAR(255)       NOT NULL,
    production_quality ENUM ('médiocre', 'normal', 'bonne', 'performante') NOT NULL,
    price            DECIMAL(10, 2)     NOT NULL,
    maintenance_cost DECIMAL(10, 2)     NOT NULL
);

CREATE TABLE IF NOT EXISTS MachineInCompany
(
    id_machine INT,
    id_company INT,
    PRIMARY KEY (id_machine, id_company),
    FOREIGN KEY (id_machine) REFERENCES Machine (id),
    FOREIGN KEY (id_company) REFERENCES Company (id)
);

CREATE TABLE IF NOT EXISTS Employee
(
    id     INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name   VARCHAR(255)       NOT NULL,
    sex   ENUM ('homme', 'femme') NOT NULL,
    seniority INT                NOT NULL,
    salary DECIMAL(10, 2)     NOT NULL,
    level  INT                NOT NULL,
    mood ENUM ('heureux', 'neutre','en colère') NOT NULL DEFAULT 'heureux',
    status   ENUM ('actif', 'inactif') NOT NULL DEFAULT 'actif',
    job ENUM ('marketing', 'vente', 'production') NOT NULL,
    health INT
);

CREATE TABLE IF NOT EXISTS EmployeeInCompany
(
    id_employee INT,
    id_company  INT,
    PRIMARY KEY (id_employee, id_company),
    FOREIGN KEY (id_employee) REFERENCES Employee (id),
    FOREIGN KEY (id_company) REFERENCES Company (id)
);

CREATE TABLE IF NOT EXISTS Local
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    level         ENUM ('petit local', 'moyen local', 'grand local') NOT NULL DEFAULT'petit local',
    size          INT                NOT NULL,
    rent          DECIMAL(10, 2)     NOT NULL,
    max_employees INT                NOT NULL,
    max_machines  INT                NOT NULL,
    id_company    INT,
    FOREIGN KEY (id_company) REFERENCES Company (id)
);

CREATE TABLE IF NOT EXISTS Event
(
    id     INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    result VARCHAR(255)       NOT NULL
);

CREATE TABLE IF NOT EXISTS CompanyEvent
(
    id_company INT,
    id_event   INT,
    PRIMARY KEY (id_company, id_event),
    FOREIGN KEY (id_company) REFERENCES Company (id),
    FOREIGN KEY (id_event) REFERENCES Event (id)
);

CREATE TABLE IF NOT EXISTS StockMaterial
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name          VARCHAR(255)       NOT NULL,
    quantity      INT                NOT NULL,
    id_company    INT,
    FOREIGN KEY (id_company) REFERENCES Company (id)
);

CREATE TABLE IF NOT EXISTS StockFinalMaterial
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name          VARCHAR(255)       NOT NULL,
    quality      INT                NOT NULL,
    quantity      INT                NOT NULL,
    id_company    INT,
    FOREIGN KEY (id_company) REFERENCES Company (id)
);