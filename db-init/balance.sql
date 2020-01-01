-- Create the BALANCE database
CREATE DATABASE BALANCE;
GO
USE BALANCE;
EXEC sys.sp_cdc_enable_db;
-- Create and populate our customer table using a single insert
CREATE TABLE TB_CUSTOMER (
  id INT IDENTITY NOT NULL PRIMARY KEY,
  ds_name VARCHAR(255) NOT NULL,
  ds_email VARCHAR(255) NOT NULL UNIQUE,
  dh_created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  dh_updated_at DATETIME DEFAULT NULL
);
INSERT INTO TB_CUSTOMER(ds_name, ds_email)
  VALUES ('Henrique Sena', 'test@test.com');
EXEC sys.sp_cdc_enable_table @source_schema = 'dbo', @source_name = 'TB_CUSTOMER', @role_name = NULL, @supports_net_changes = 0;
-- Create and populate our balance table using a single insert
CREATE TABLE TB_BALANCE (
  id INT IDENTITY NOT NULL PRIMARY KEY,
  id_customer INT NOT NULL,
  vl_balance DECIMAL(11,4) NOT NULL,
  dh_created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  dh_updated_at DATETIME DEFAULT NULL,
  FOREIGN KEY (id_customer) REFERENCES TB_CUSTOMER(id)
);
INSERT INTO TB_BALANCE(id_customer, vl_balance)
  VALUES ((SELECT ID FROM TB_CUSTOMER WHERE ds_email = 'test@test.com'), 100.00);
EXEC sys.sp_cdc_enable_table @source_schema = 'dbo', @source_name = 'TB_BALANCE', @role_name = NULL, @supports_net_changes = 0;
GO