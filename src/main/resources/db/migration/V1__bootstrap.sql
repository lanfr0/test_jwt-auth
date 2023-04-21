CREATE TABLE APP_USER
(
    ID         VARCHAR(255) PRIMARY KEY,
    FIRST_NAME VARCHAR(255)             NOT NULL,
    LAST_NAME  VARCHAR(255)             NOT NULL,
    EMAIL      VARCHAR(255)             NOT NULL,
    PASSWORD   VARCHAR(255)             NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    UPDATED_AT TIMESTAMP WITH TIME ZONE NOT NULL
);
