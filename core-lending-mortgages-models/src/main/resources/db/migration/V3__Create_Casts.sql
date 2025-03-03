-- V3 - CASTS USING "WITH INOUT AS IMPLICIT" FOR ALL MORTGAGE ENUMS

-------------------------
-- application_status
-------------------------
CREATE CAST (varchar AS application_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- application_channel
-------------------------
CREATE CAST (varchar AS application_channel)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- employment_type
-------------------------
CREATE CAST (varchar AS employment_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- residence_type
-------------------------
CREATE CAST (varchar AS residence_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- purpose
-------------------------
CREATE CAST (varchar AS purpose)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- property_type
-------------------------
CREATE CAST (varchar AS property_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- property_status
-------------------------
CREATE CAST (varchar AS property_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- property_use
-------------------------
CREATE CAST (varchar AS property_use)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- property_condition
-------------------------
CREATE CAST (varchar AS property_condition)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- location_rating
-------------------------
CREATE CAST (varchar AS location_rating)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- energy_rating
-------------------------
CREATE CAST (varchar AS energy_rating)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- appraisal_type
-------------------------
CREATE CAST (varchar AS appraisal_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- contract_status
-------------------------
CREATE CAST (varchar AS contract_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- rate_type
-------------------------
CREATE CAST (varchar AS rate_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- payment_status
-------------------------
CREATE CAST (varchar AS payment_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- insurance_type
-------------------------
CREATE CAST (varchar AS insurance_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- premium_frequency
-------------------------
CREATE CAST (varchar AS premium_frequency)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- document_type
-------------------------
CREATE CAST (varchar AS document_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- notification_type
-------------------------
CREATE CAST (varchar AS notification_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- priority
-------------------------
CREATE CAST (varchar AS priority)
    WITH INOUT
    AS IMPLICIT;
