-- V3 - CASTS USING "WITH INOUT AS IMPLICIT" FOR ALL MORTGAGE ENUMS AND CREATE INDEXES

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

-- ========================================================================
-- CREATE INDEXES ON UUID COLUMNS FOR PERFORMANCE
-- ========================================================================

CREATE INDEX idx_mortgage_application_id ON mortgage_application (mortgage_application_id);
CREATE INDEX idx_mortgage_property_id ON mortgage_property (property_id);
CREATE INDEX idx_mortgage_contract_id ON mortgage_contract (mortgage_contract_id);
CREATE INDEX idx_mortgage_appraisal_id ON mortgage_appraisal (appraisal_id);
CREATE INDEX idx_mortgage_payment_schedule_id ON mortgage_payment_schedule (schedule_id);
CREATE INDEX idx_mortgage_payment_record_id ON mortgage_payment_record (payment_record_id);
CREATE INDEX idx_mortgage_insurance_id ON mortgage_insurance (insurance_id);
CREATE INDEX idx_mortgage_document_id ON mortgage_document (document_id);
CREATE INDEX idx_mortgage_notification_id ON mortgage_notification (notification_id);
CREATE INDEX idx_mortgage_disbursement_id ON mortgage_disbursement (disbursement_id);
CREATE INDEX idx_mortgage_application_status_history_id ON mortgage_application_status_history (status_history_id);

-- Foreign key indexes for performance
CREATE INDEX idx_mortgage_application_property_id ON mortgage_application (property_id);
CREATE INDEX idx_mortgage_appraisal_property_id ON mortgage_appraisal (property_id);
CREATE INDEX idx_mortgage_appraisal_application_id ON mortgage_appraisal (mortgage_application_id);
CREATE INDEX idx_mortgage_contract_application_id ON mortgage_contract (mortgage_application_id);
CREATE INDEX idx_mortgage_contract_property_id ON mortgage_contract (property_id);
CREATE INDEX idx_mortgage_payment_schedule_contract_id ON mortgage_payment_schedule (mortgage_contract_id);
CREATE INDEX idx_mortgage_payment_record_contract_id ON mortgage_payment_record (mortgage_contract_id);
CREATE INDEX idx_mortgage_payment_record_schedule_id ON mortgage_payment_record (payment_schedule_id);
CREATE INDEX idx_mortgage_insurance_contract_id ON mortgage_insurance (mortgage_contract_id);
CREATE INDEX idx_mortgage_document_application_id ON mortgage_document (mortgage_application_id);
CREATE INDEX idx_mortgage_notification_contract_id ON mortgage_notification (mortgage_contract_id);
CREATE INDEX idx_mortgage_disbursement_contract_id ON mortgage_disbursement (mortgage_contract_id);
CREATE INDEX idx_mortgage_application_status_hist_app_id ON mortgage_application_status_history (mortgage_application_id);