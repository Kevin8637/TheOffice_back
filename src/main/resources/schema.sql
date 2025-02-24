CREATE TABLE IF NOT EXISTS USER
(
    id       INT AUTO_INCREMENT     NOT NULL PRIMARY KEY,
    email    VARCHAR(255) UNIQUE    NOT NULL,
    username VARCHAR(255)           NOT NULL,
    password VARCHAR(255)           NOT NULL,
    role     ENUM ('user', 'admin') NOT NULL DEFAULT 'user',
    wallet    DECIMAL(10, 2)     NOT NULL DEFAULT 0.00
);

CREATE TABLE IF NOT EXISTS COMPANY
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    sector       VARCHAR(255)       NOT NULL,
    name          VARCHAR(255)       NOT NULL,
    creation_date DATE               NOT NULL,
    id_user       INT,
    FOREIGN KEY (id_user) REFERENCES USER (id)
);

CREATE TABLE IF NOT EXISTS LOAN
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    loan_amount   DECIMAL(10, 2)     NOT NULL,
    interest_rate DECIMAL(5, 2)      NOT NULL,
    duration      INT                NOT NULL,
    rest DECIMAL(10, 2)     NOT NULL,
    id_user       INT,
    FOREIGN KEY (id_user) REFERENCES USER (id)
);

CREATE TABLE IF NOT EXISTS SUPPLIER
(
    id         INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name       VARCHAR(255)       NOT NULL,
    price      DECIMAL(10, 2)     NOT NULL,
    quality    VARCHAR(100)       NOT NULL,
    id_company INT,
    FOrEIGN KEY (id_company) REFERENCES COMPANY (id)
);

CREATE TABLE IF NOT EXISTS CYCLE
(
    id           INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    cost         DECIMAL(10, 2)     NOT NULL,
    employees    INT                NOT NULL,
    productivity INT                NOT NULL,
    popularity   INT                NOT NULL,
    step         INT                NOT NULL,
    id_company   INT,
    FOREIGN KEY (id_company) REFERENCES COMPANY (id)
);

CREATE TABLE IF NOT EXISTS MACHINE
(
    id               INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name             VARCHAR(255)       NOT NULL,
    production_quality ENUM ('médiocre', 'normal', 'bonne', 'performante') NOT NULL,
    price            DECIMAL(10, 2)     NOT NULL,
    maintenance_cost DECIMAL(10, 2)     NOT NULL
);

CREATE TABLE IF NOT EXISTS MACHINE_IN_COMPANY
(
    id_machine INT,
    id_company INT,
    PRIMARY KEY (id_machine, id_company),
    FOREIGN KEY (id_machine) REFERENCES MACHINE (id),
    FOREIGN KEY (id_company) REFERENCES COMPANY (id)
);

CREATE TABLE IF NOT EXISTS EMPLOYEE
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

CREATE TABLE IF NOT EXISTS EMPLOYEE_IN_COMPANY
(
    id_employee INT,
    id_company  INT,
    PRIMARY KEY (id_employee, id_company),
    FOREIGN KEY (id_employee) REFERENCES EMPLOYEE (id),
    FOREIGN KEY (id_company) REFERENCES COMPANY (id)
);

CREATE TABLE IF NOT EXISTS LOCAL
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    level         ENUM ('petit local', 'moyen local', 'grand local') NOT NULL DEFAULT'petit local',
    size          INT                NOT NULL,
    rent          DECIMAL(10, 2)     NOT NULL,
    max_employees INT                NOT NULL,
    max_machines  INT                NOT NULL,
    id_company    INT,
    FOREIGN KEY (id_company) REFERENCES COMPANY (id)
);

CREATE TABLE IF NOT EXISTS EVENT
(
    id     INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    result VARCHAR(255)       NOT NULL
);

CREATE TABLE IF NOT EXISTS COMPANY_EVENT
(
    id_company INT,
    id_event   INT,
    PRIMARY KEY (id_company, id_event),
    FOREIGN KEY (id_company) REFERENCES COMPANY (id),
    FOREIGN KEY (id_event) REFERENCES EVENT (id)
);

CREATE TABLE IF NOT EXISTS STOCK_MATERIAL
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name          VARCHAR(255)       NOT NULL,
    quantity      INT                NOT NULL,
    id_company    INT,
    FOREIGN KEY (id_company) REFERENCES COMPANY (id)
);

CREATE TABLE IF NOT EXISTS STOCK_FINAL_MATERIAL
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name          VARCHAR(255)       NOT NULL,
    quality      INT                NOT NULL,
    quantity      INT                NOT NULL,
    id_company    INT,
    FOREIGN KEY (id_company) REFERENCES COMPANY (id)
);