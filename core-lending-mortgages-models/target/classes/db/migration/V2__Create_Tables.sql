-- V2 - CREATE TABLES FOR THE MORTGAGE SUBMODULE WITH UUID PRIMARY KEYS

-- Enable uuid extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ========================================================================
-- TABLE: mortgage_property (base table, no dependencies)
-- ========================================================================
CREATE TABLE IF NOT EXISTS mortgage_property (
    property_id              UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    property_type            property_type NOT NULL,
    property_status          property_status NOT NULL,
    property_use             property_use NOT NULL,
    address_line1            VARCHAR(255),
    address_line2            VARCHAR(255),
    city                     VARCHAR(100),
    state                    VARCHAR(100),
    postal_code              VARCHAR(20),
    country_code             VARCHAR(10),
    land_area                DECIMAL(18,2),
    built_area               DECIMAL(18,2),
    construction_year        INT,
    renovation_year          INT,
    title_number             VARCHAR(100),
    cadastral_reference      VARCHAR(100),
    legal_description        TEXT,
    total_rooms              INT,
    total_bedrooms           INT,
    total_bathrooms          INT,
    has_parking              BOOLEAN DEFAULT FALSE,
    parking_spaces           INT,
    has_storage              BOOLEAN DEFAULT FALSE,
    storage_area             DECIMAL(18,2),
    has_elevator             BOOLEAN DEFAULT FALSE,
    floor_number             INT,
    energy_rating            energy_rating NOT NULL,
    restrictions             TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW()
);

-- ========================================================================
-- TABLE: mortgage_application
-- ========================================================================
CREATE TABLE IF NOT EXISTS mortgage_application (
    mortgage_application_id   UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    applicant_id             UUID NOT NULL, -- external reference (Customer domain)
    co_applicant_id          UUID,         -- optional second applicant
    property_id              UUID NOT NULL, -- references mortgage_property
    product_id               UUID,          -- references some product setup
    application_status       application_status NOT NULL,
    application_channel      application_channel NOT NULL,
    requested_amount         DECIMAL(18,2),
    requested_term_months    INT,
    down_payment             DECIMAL(18,2),
    monthly_income           DECIMAL(18,2),
    monthly_expenses         DECIMAL(18,2),
    employment_type          employment_type NOT NULL,
    residence_type           residence_type NOT NULL,
    years_at_current_job     INT,
    years_at_current_address INT,
    purpose                  purpose NOT NULL,
    existing_customer        BOOLEAN DEFAULT FALSE,
    remarks                  TEXT,
    assigned_to              VARCHAR(100),
    submission_date          TIMESTAMP,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_mortgage_application_property
        FOREIGN KEY (property_id) REFERENCES mortgage_property (property_id)
);

-- ========================================================================
-- TABLE: mortgage_application_status_history
-- ========================================================================
CREATE TABLE IF NOT EXISTS mortgage_application_status_history (
    status_history_id        UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    mortgage_application_id  UUID NOT NULL,
    old_status               application_status,
    new_status               application_status NOT NULL,
    changed_by               VARCHAR(100),
    change_reason            VARCHAR(100),
    comments                 TEXT,
    changed_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_app_status_hist
        FOREIGN KEY (mortgage_application_id) REFERENCES mortgage_application (mortgage_application_id)
);

-- ========================================================================
-- TABLE: mortgage_appraisal
-- ========================================================================
CREATE TABLE IF NOT EXISTS mortgage_appraisal (
    appraisal_id             UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    property_id              UUID NOT NULL,
    mortgage_application_id  UUID NOT NULL,
    appraiser_name           VARCHAR(255),
    license_number           VARCHAR(100),
    appraisal_type           appraisal_type NOT NULL,
    market_value             DECIMAL(18,2),
    rental_value             DECIMAL(18,2),
    replacement_cost         DECIMAL(18,2),
    land_value               DECIMAL(18,2),
    building_value           DECIMAL(18,2),
    property_condition       property_condition NOT NULL,
    location_rating          location_rating NOT NULL,
    comparable_properties    TEXT,  -- JSON array
    appraisal_date           DATE,
    expiry_date              DATE,
    requires_repairs         BOOLEAN DEFAULT FALSE,
    required_repairs         TEXT,
    repair_cost              DECIMAL(18,2),
    assumptions              TEXT,
    limitations              TEXT,
    methodology              TEXT,
    comments                 TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_property_appraisal
        FOREIGN KEY (property_id) REFERENCES mortgage_property (property_id),
    CONSTRAINT fk_app_appraisal
        FOREIGN KEY (mortgage_application_id) REFERENCES mortgage_application (mortgage_application_id)
);

-- ========================================================================
-- TABLE: mortgage_contract
-- ========================================================================
CREATE TABLE IF NOT EXISTS mortgage_contract (
    mortgage_contract_id     UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    mortgage_application_id  UUID NOT NULL,
    property_id              UUID NOT NULL,
    contract_number          VARCHAR(50) NOT NULL,
    contract_status          contract_status NOT NULL,
    loan_amount              DECIMAL(18,2) NOT NULL,
    interest_rate            DECIMAL(9,4) NOT NULL,
    rate_type                rate_type NOT NULL,
    reference_rate           VARCHAR(50),
    margin_rate              DECIMAL(9,4),
    term_months              INT NOT NULL,
    start_date               DATE NOT NULL,
    maturity_date            DATE NOT NULL,
    monthly_payment          DECIMAL(18,2),
    early_repayment_fee      DECIMAL(9,4),
    assumable                BOOLEAN DEFAULT FALSE,
    tax_rate                 DECIMAL(9,4) DEFAULT 0,
    special_conditions       TEXT,  -- JSON format
    notary_reference         VARCHAR(100),
    signing_date             TIMESTAMP,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_mortgage_app_contract
        FOREIGN KEY (mortgage_application_id) REFERENCES mortgage_application (mortgage_application_id),
    CONSTRAINT fk_property_contract
        FOREIGN KEY (property_id) REFERENCES mortgage_property (property_id)
);

-- ========================================================================
-- TABLE: mortgage_insurance
-- ========================================================================
CREATE TABLE IF NOT EXISTS mortgage_insurance (
    insurance_id             UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    mortgage_contract_id     UUID NOT NULL,
    insurance_type           insurance_type NOT NULL,
    policy_number            VARCHAR(100) NOT NULL,
    provider_name            VARCHAR(255),
    provider_code            VARCHAR(50),
    coverage_amount          DECIMAL(18,2) NOT NULL,
    deductible_amount        DECIMAL(18,2) DEFAULT 0,
    start_date               DATE NOT NULL,
    end_date                 DATE,
    annual_premium           DECIMAL(18,2) DEFAULT 0,
    premium_frequency        premium_frequency NOT NULL,
    premium_amount           DECIMAL(18,2) DEFAULT 0,
    bank_beneficiary         BOOLEAN DEFAULT TRUE,
    is_active                BOOLEAN DEFAULT TRUE,
    last_payment_date        DATE,
    next_payment_date        DATE,
    coverage_details         TEXT,  -- JSON format
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_mortgage_contract_ins
        FOREIGN KEY (mortgage_contract_id) REFERENCES mortgage_contract (mortgage_contract_id)
);

-- ========================================================================
-- TABLE: mortgage_disbursement
-- ========================================================================
CREATE TABLE IF NOT EXISTS mortgage_disbursement (
    disbursement_id          UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    mortgage_contract_id     UUID NOT NULL,
    disbursement_amount      DECIMAL(18,2) NOT NULL,
    disbursement_date        DATE NOT NULL,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_mortgage_contract_disb
        FOREIGN KEY (mortgage_contract_id) REFERENCES mortgage_contract (mortgage_contract_id)
);

-- ========================================================================
-- TABLE: mortgage_payment_schedule
-- ========================================================================
CREATE TABLE IF NOT EXISTS mortgage_payment_schedule (
    schedule_id              UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    mortgage_contract_id     UUID NOT NULL,
    installment_number       INT NOT NULL,
    due_date                 DATE NOT NULL,
    principal_amount         DECIMAL(18,2) NOT NULL,
    interest_amount          DECIMAL(18,2) NOT NULL,
    fee_amount               DECIMAL(18,2) DEFAULT 0,
    escrow_amount            DECIMAL(18,2) DEFAULT 0,
    total_amount             DECIMAL(18,2) NOT NULL,
    payment_status           payment_status NOT NULL,
    paid_date                DATE,
    outstanding_balance      DECIMAL(18,2) DEFAULT 0,
    late_fee_amount          DECIMAL(18,2) DEFAULT 0,
    days_late                INT DEFAULT 0,
    payment_breakdown        TEXT, -- JSON detail
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_mortgage_contract_sched
        FOREIGN KEY (mortgage_contract_id) REFERENCES mortgage_contract (mortgage_contract_id)
);

-- ========================================================================
-- TABLE: mortgage_payment_record
-- ========================================================================
CREATE TABLE IF NOT EXISTS mortgage_payment_record (
    payment_record_id        UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    mortgage_contract_id     UUID NOT NULL,
    payment_schedule_id      UUID, -- optional link to mortgage_payment_schedule
    transaction_id           UUID, -- external Payment domain reference
    payment_amount           DECIMAL(18,2) NOT NULL,
    payment_date             DATE NOT NULL,
    is_partial_payment       BOOLEAN DEFAULT FALSE,
    note                     TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_mortgage_contract_pay
        FOREIGN KEY (mortgage_contract_id) REFERENCES mortgage_contract (mortgage_contract_id),
    CONSTRAINT fk_sched_payment
        FOREIGN KEY (payment_schedule_id) REFERENCES mortgage_payment_schedule (schedule_id)
);

-- ========================================================================
-- TABLE: mortgage_document
-- ========================================================================
CREATE TABLE IF NOT EXISTS mortgage_document (
    document_id              UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    mortgage_application_id  UUID NOT NULL,
    document_type            document_type NOT NULL,
    document_reference       VARCHAR(100),
    document_date            DATE,
    is_verified              BOOLEAN DEFAULT FALSE,
    verified_by              VARCHAR(100),
    verified_at              TIMESTAMP,
    verification_notes       TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_mortgage_app_doc
        FOREIGN KEY (mortgage_application_id) REFERENCES mortgage_application (mortgage_application_id)
);

-- ========================================================================
-- TABLE: mortgage_notification
-- ========================================================================
CREATE TABLE IF NOT EXISTS mortgage_notification (
    notification_id          UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    mortgage_contract_id     UUID NOT NULL,
    notification_type        notification_type NOT NULL,
    priority                 priority NOT NULL,
    recipient               VARCHAR(100),
    message                  TEXT,
    is_sent                  BOOLEAN DEFAULT FALSE,
    sent_at                  TIMESTAMP,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_mortgage_contract_notif
        FOREIGN KEY (mortgage_contract_id) REFERENCES mortgage_contract (mortgage_contract_id)
);