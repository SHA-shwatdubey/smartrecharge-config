-- SmartRecharge Database Initialization Script
-- Creates all necessary databases for the microservices system

-- ============================================
-- 1. CREATE DATABASES
-- ============================================

CREATE DATABASE customer_db;
CREATE DATABASE recharge_db;
CREATE DATABASE processing_db;

-- ============================================
-- 2. GRANT PRIVILEGES TO POSTGRES USER
-- ============================================

GRANT ALL PRIVILEGES ON DATABASE customer_db TO postgres;
GRANT ALL PRIVILEGES ON DATABASE recharge_db TO postgres;
GRANT ALL PRIVILEGES ON DATABASE processing_db TO postgres;

-- ============================================
-- 3. ALTER POSTGRES USER (Optional - for enhanced security)
-- ============================================

ALTER ROLE postgres WITH CREATEDB CREATEROLE;

-- ============================================
-- Note:
-- ============================================
-- Tables will be auto-created by Hibernate ORM
-- using the following entities:
--   - Customer (customer_db)
--   - Recharge (recharge_db)
--   - RechargeTransaction (processing_db)
--
-- Database Configuration:
--   Host: localhost
--   Port: 5432
--   Username: postgres
--   Password: root
--
-- To verify databases are created, run:
--   psql -U postgres -l


