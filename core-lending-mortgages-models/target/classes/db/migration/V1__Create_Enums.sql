-- V1 - CREATE ENUMS FOR MORTGAGE SUBMODULE

-------------------------
-- Mortgage Application
-------------------------
CREATE TYPE application_status AS ENUM (
    'DRAFT',
    'SUBMITTED',
    'UNDER_REVIEW',
    'APPROVED',
    'REJECTED',
    'CLOSED'
);

CREATE TYPE application_channel AS ENUM (
    'ONLINE',
    'BRANCH',
    'PARTNER',
    'OTHER'
);

CREATE TYPE employment_type AS ENUM (
    'SALARIED',
    'SELF_EMPLOYED',
    'UNEMPLOYED',
    'RETIRED',
    'STUDENT',
    'OTHER'
);

CREATE TYPE residence_type AS ENUM (
    'OWNED',
    'RENTED',
    'FAMILY_HOME',
    'COMPANY_PROVIDED',
    'OTHER'
);

CREATE TYPE purpose AS ENUM (
    'PURCHASE',
    'REFINANCE',
    'CONSTRUCTION',
    'EQUITY_RELEASE',
    'OTHER'
);

-------------------------
-- Mortgage Property
-------------------------
CREATE TYPE property_type AS ENUM (
    'RESIDENTIAL',
    'COMMERCIAL',
    'LAND',
    'INDUSTRIAL'
);

CREATE TYPE property_status AS ENUM (
    'NEW',
    'USED',
    'OFF_PLAN',
    'UNDER_CONSTRUCTION'
);

CREATE TYPE property_use AS ENUM (
    'PRIMARY_RESIDENCE',
    'SECOND_HOME',
    'INVESTMENT'
);

CREATE TYPE property_condition AS ENUM (
    'EXCELLENT',
    'GOOD',
    'FAIR',
    'POOR'
);

CREATE TYPE location_rating AS ENUM (
    'A',
    'B',
    'C',
    'D',
    'E'
);

CREATE TYPE energy_rating AS ENUM (
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    'UNKNOWN'
);

-------------------------
-- Mortgage Appraisal
-------------------------
CREATE TYPE appraisal_type AS ENUM (
    'FULL_APPRAISAL',
    'DRIVE_BY',
    'AUTOMATED',
    'DESKTOP',
    'OTHER'
);

-------------------------
-- Mortgage Contract
-------------------------
CREATE TYPE contract_status AS ENUM (
    'ACTIVE',
    'DISBURSED',
    'REPAID',
    'DEFAULTED',
    'TERMINATED'
);

CREATE TYPE rate_type AS ENUM (
    'FIXED',
    'VARIABLE',
    'HYBRID'
);

-------------------------
-- Mortgage Payment Schedule
-------------------------
CREATE TYPE payment_status AS ENUM (
    'SCHEDULED',
    'PAID',
    'OVERDUE',
    'PARTIALLY_PAID',
    'WAIVED'
);

-------------------------
-- Mortgage Insurance
-------------------------
CREATE TYPE insurance_type AS ENUM (
    'HOMEOWNERS',
    'FIRE',
    'FLOOD',
    'LIFE',
    'MORTGAGE_GUARANTEE',
    'OTHER'
);

CREATE TYPE premium_frequency AS ENUM (
    'MONTHLY',
    'QUARTERLY',
    'SEMIANNUAL',
    'ANNUAL'
);

-------------------------
-- Mortgage Document
-------------------------
CREATE TYPE document_type AS ENUM (
    'ID_PROOF',
    'INCOME_PROOF',
    'PURCHASE_AGREEMENT',
    'PROPERTY_DEED',
    'CREDIT_REPORT',
    'OTHER'
);

-------------------------
-- Mortgage Notification
-------------------------
CREATE TYPE notification_type AS ENUM (
    'PAYMENT_REMINDER',
    'RATE_CHANGE',
    'INSURANCE_RENEWAL',
    'APPROVAL_STATUS',
    'OTHER'
);

CREATE TYPE priority AS ENUM (
    'LOW',
    'MEDIUM',
    'HIGH',
    'CRITICAL'
);