/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

-- ========================================================================
-- V1: Create Mortgage Schema
-- ========================================================================
-- This migration creates the complete mortgage microservice schema.
-- 
-- SCOPE: This microservice handles ONLY mortgage-specific contractual
-- terms and legal documentation. All operational servicing (payments,
-- disbursements, accruals, notifications) is handled by the
-- core-lending-loan-servicing microservice.
-- ========================================================================

-- ========================================================================
-- ENUMERATIONS
-- ========================================================================

-- Agreement Status
CREATE TYPE agreement_status AS ENUM (
    'DRAFT',        -- Agreement is being drafted
    'ACTIVE',       -- Agreement is active
    'SUSPENDED',    -- Agreement is temporarily suspended
    'CLOSED',       -- Agreement is closed/completed
    'CANCELLED'     -- Agreement was cancelled
);

COMMENT ON TYPE agreement_status IS 'Status of the mortgage agreement lifecycle';

-- Mortgage Type (Global mortgage products)
CREATE TYPE mortgage_type AS ENUM (
    -- Conventional Mortgages
    'CONVENTIONAL_FIXED',           -- Fixed-rate conventional mortgage
    'CONVENTIONAL_VARIABLE',        -- Variable/adjustable-rate mortgage (ARM)
    'CONVENTIONAL_HYBRID',          -- Hybrid ARM (fixed then variable)
    'CONVENTIONAL_INTEREST_ONLY',   -- Interest-only mortgage
    'CONVENTIONAL_BALLOON',         -- Balloon payment mortgage
    
    -- Government-Backed Mortgages
    'FHA',                          -- Federal Housing Administration (USA)
    'VA',                           -- Veterans Affairs (USA)
    'USDA',                         -- USDA Rural Development (USA)
    'CMHC',                         -- Canada Mortgage and Housing Corporation
    'HELP_TO_BUY',                  -- Help to Buy (UK)
    'KFW',                          -- KfW Förderbank (Germany)
    'INFONAVIT',                    -- Instituto del Fondo Nacional de la Vivienda (Mexico)
    'FOVISSSTE',                    -- Fondo de la Vivienda del ISSSTE (Mexico)
    
    -- Islamic Finance Mortgages
    'MURABAHA',                     -- Cost-plus financing (Islamic)
    'IJARA',                        -- Lease-to-own (Islamic)
    'MUSHARAKA',                    -- Partnership/joint ownership (Islamic)
    'DIMINISHING_MUSHARAKA',        -- Diminishing partnership (Islamic)
    'ISTISNA',                      -- Construction financing (Islamic)
    
    -- Reverse Mortgages
    'REVERSE_MORTGAGE',             -- Standard reverse mortgage
    'HECM',                         -- Home Equity Conversion Mortgage (USA)
    'LIFETIME_MORTGAGE',            -- Lifetime mortgage (UK)
    
    -- Specialized Mortgages
    'BRIDGE_LOAN',                  -- Bridge financing
    'CONSTRUCTION_MORTGAGE',        -- Construction-to-permanent
    'LAND_LOAN',                    -- Land acquisition
    'RENOVATION_MORTGAGE',          -- Renovation/rehabilitation
    'EQUITY_RELEASE',               -- Equity release products
    'SHARED_EQUITY',                -- Shared equity/appreciation
    'SHARED_OWNERSHIP',             -- Shared ownership schemes
    'RENT_TO_OWN',                  -- Rent-to-own programs
    'LEASE_PURCHASE',               -- Lease-purchase agreements
    
    -- Commercial/Investment
    'COMMERCIAL_MORTGAGE',          -- Commercial property
    'INVESTMENT_PROPERTY',          -- Investment/rental property
    'MULTIFAMILY',                  -- Multifamily residential
    'MIXED_USE',                    -- Mixed-use property
    
    -- International Specific
    'HYPOTHEEK',                    -- Dutch mortgage
    'BAUSPAREN',                    -- German building savings
    'PRET_IMMOBILIER',              -- French mortgage
    'HIPOTECA',                     -- Spanish/Latin American mortgage
    'MUTUO_IPOTECARIO',             -- Italian mortgage
    
    -- Other
    'OTHER'                         -- Other mortgage types
);

COMMENT ON TYPE mortgage_type IS 'Global mortgage product types including conventional, government-backed, Islamic finance, reverse mortgages, and specialized products';

-- Lien Position
CREATE TYPE lien_position AS ENUM (
    'FIRST',        -- First lien/mortgage
    'SECOND',       -- Second lien/mortgage
    'THIRD',        -- Third lien/mortgage
    'SUBORDINATE'   -- Subordinate lien
);

COMMENT ON TYPE lien_position IS 'Legal priority position of the mortgage lien';

-- Rate Type
CREATE TYPE rate_type AS ENUM (
    'FIXED',        -- Fixed interest rate for entire term
    'VARIABLE',     -- Variable/adjustable rate
    'HYBRID'        -- Hybrid (fixed period then variable)
);

COMMENT ON TYPE rate_type IS 'Type of interest rate structure (contractual term)';

-- ========================================================================
-- TABLES
-- ========================================================================

-- Mortgage Agreement
CREATE TABLE mortgage_agreement (
    -- Primary Key
    mortgage_agreement_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    
    -- External References (to other microservices)
    application_id UUID,                    -- Reference to LoanApplication (Loan Origination)
    servicing_case_id UUID,                 -- Reference to LoanServicingCase (Loan Servicing)
    proposed_offer_id UUID,                 -- Reference to ProposedOffer (Loan Origination)
    
    -- Mortgage-Specific Agreement Terms
    agreement_status agreement_status NOT NULL DEFAULT 'DRAFT',
    mortgage_type mortgage_type NOT NULL,
    lien_position lien_position NOT NULL DEFAULT 'FIRST',
    
    -- Interest Rate Terms (Contractual - what was AGREED)
    rate_type rate_type NOT NULL,
    initial_interest_rate DECIMAL(5,4),     -- Initial rate at contract start (e.g., 3.5000 = 3.5%)
    reference_rate VARCHAR(50),             -- e.g., "EURIBOR_12M", "LIBOR_3M", "SOFR", "PRIME"
    margin_rate DECIMAL(5,4),               -- Margin/spread added to reference rate
    fixed_rate_period_months INTEGER,       -- For hybrid mortgages - initial fixed period
    rate_cap DECIMAL(5,4),                  -- Maximum interest rate cap (contractual limit)
    rate_floor DECIMAL(5,4),                -- Minimum interest rate floor (contractual limit)
    periodic_rate_cap DECIMAL(5,4),         -- Maximum rate change per adjustment period
    
    -- Islamic Finance Specific (if applicable)
    profit_rate DECIMAL(5,4),               -- Profit rate for Islamic mortgages
    rental_rate DECIMAL(5,4),               -- Rental rate for Ijara mortgages
    bank_ownership_percentage DECIMAL(5,2), -- For Musharaka/diminishing Musharaka
    purchase_price DECIMAL(15,2),           -- Purchase price for Murabaha
    markup_amount DECIMAL(15,2),            -- Markup for Murabaha
    
    -- Mortgage Legal and Regulatory
    notary_name VARCHAR(255),               -- Notary who formalized the contract
    notary_registration_number VARCHAR(100), -- Notary's official registration
    deed_number VARCHAR(100),               -- Public deed number
    deed_date DATE,                         -- Date of deed execution
    registry_office VARCHAR(255),           -- Property registry office
    registry_volume VARCHAR(50),            -- Registry volume number
    registry_book VARCHAR(50),              -- Registry book number
    registry_folio VARCHAR(50),             -- Registry folio number
    registry_inscription_date DATE,         -- Date registered in property registry
    
    -- Mortgage-Specific Contractual Features (What's ALLOWED per contract)
    is_assumable BOOLEAN DEFAULT FALSE,     -- Can mortgage be assumed by new buyer
    is_portable BOOLEAN DEFAULT FALSE,      -- Can be transferred to new property
    is_recourse BOOLEAN DEFAULT TRUE,       -- Recourse vs non-recourse loan
    allows_early_repayment BOOLEAN DEFAULT TRUE, -- Early repayment allowed per contract
    early_repayment_penalty_rate DECIMAL(5,2), -- Contractual penalty % for early repayment
    early_repayment_penalty_period_months INTEGER, -- Period during which penalty applies
    allows_partial_prepayment BOOLEAN DEFAULT TRUE, -- Partial prepayments allowed per contract
    partial_prepayment_min_amount DECIMAL(15,2), -- Minimum amount for partial prepayment
    partial_prepayment_max_per_year DECIMAL(15,2), -- Maximum prepayment per year
    subrogation_allowed BOOLEAN DEFAULT TRUE, -- Can be transferred to another lender
    subrogation_fee DECIMAL(15,2),          -- Contractual fee for subrogation
    
    -- Audit and Lifecycle
    agreement_signed_date DATE,             -- Date agreement was signed
    agreement_effective_date DATE,          -- Date agreement becomes effective
    created_by VARCHAR(255),                -- User who created the agreement
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255),                -- User who last updated
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE mortgage_agreement IS 'Mortgage-specific agreement terms. Stores CONTRACTUAL terms (what was AGREED), not operational servicing data (handled by loan servicing microservice).';

-- Column Comments
COMMENT ON COLUMN mortgage_agreement.mortgage_agreement_id IS 'Unique identifier for the mortgage agreement';
COMMENT ON COLUMN mortgage_agreement.application_id IS 'Reference to loan application in loan origination microservice';
COMMENT ON COLUMN mortgage_agreement.servicing_case_id IS 'Reference to loan servicing case in loan servicing microservice';
COMMENT ON COLUMN mortgage_agreement.proposed_offer_id IS 'Reference to proposed offer in loan origination microservice';
COMMENT ON COLUMN mortgage_agreement.agreement_status IS 'Current status of the agreement (DRAFT, ACTIVE, SUSPENDED, CLOSED, CANCELLED)';
COMMENT ON COLUMN mortgage_agreement.mortgage_type IS 'Type of mortgage product (conventional, Islamic, reverse, government-backed, etc.)';
COMMENT ON COLUMN mortgage_agreement.lien_position IS 'Legal priority position of the mortgage lien (FIRST, SECOND, THIRD, SUBORDINATE)';
COMMENT ON COLUMN mortgage_agreement.rate_type IS 'Type of interest rate structure (FIXED, VARIABLE, HYBRID)';
COMMENT ON COLUMN mortgage_agreement.initial_interest_rate IS 'Initial interest rate at contract start (contractual term)';
COMMENT ON COLUMN mortgage_agreement.reference_rate IS 'Reference rate index for variable/hybrid mortgages (e.g., EURIBOR, LIBOR, SOFR)';
COMMENT ON COLUMN mortgage_agreement.margin_rate IS 'Margin/spread added to reference rate for variable/hybrid mortgages';
COMMENT ON COLUMN mortgage_agreement.fixed_rate_period_months IS 'Initial fixed rate period for hybrid mortgages (in months)';
COMMENT ON COLUMN mortgage_agreement.rate_cap IS 'Maximum interest rate cap (contractual limit)';
COMMENT ON COLUMN mortgage_agreement.rate_floor IS 'Minimum interest rate floor (contractual limit)';
COMMENT ON COLUMN mortgage_agreement.periodic_rate_cap IS 'Maximum rate change per adjustment period (contractual limit)';
COMMENT ON COLUMN mortgage_agreement.profit_rate IS 'Profit rate for Islamic finance mortgages';
COMMENT ON COLUMN mortgage_agreement.rental_rate IS 'Rental rate for Ijara (Islamic lease-to-own) mortgages';
COMMENT ON COLUMN mortgage_agreement.bank_ownership_percentage IS 'Bank ownership percentage for Musharaka (Islamic partnership) mortgages';
COMMENT ON COLUMN mortgage_agreement.purchase_price IS 'Purchase price for Murabaha (Islamic cost-plus) mortgages';
COMMENT ON COLUMN mortgage_agreement.markup_amount IS 'Markup amount for Murabaha (Islamic cost-plus) mortgages';
COMMENT ON COLUMN mortgage_agreement.notary_name IS 'Name of notary who formalized the mortgage contract';
COMMENT ON COLUMN mortgage_agreement.notary_registration_number IS 'Official registration number of the notary';
COMMENT ON COLUMN mortgage_agreement.deed_number IS 'Public deed number for the mortgage';
COMMENT ON COLUMN mortgage_agreement.deed_date IS 'Date the deed was executed';
COMMENT ON COLUMN mortgage_agreement.registry_office IS 'Property registry office where mortgage is registered';
COMMENT ON COLUMN mortgage_agreement.registry_volume IS 'Registry volume number';
COMMENT ON COLUMN mortgage_agreement.registry_book IS 'Registry book number';
COMMENT ON COLUMN mortgage_agreement.registry_folio IS 'Registry folio number';
COMMENT ON COLUMN mortgage_agreement.registry_inscription_date IS 'Date mortgage was registered in property registry';
COMMENT ON COLUMN mortgage_agreement.is_assumable IS 'Whether mortgage can be assumed by new buyer (contractual term)';
COMMENT ON COLUMN mortgage_agreement.is_portable IS 'Whether mortgage can be transferred to new property (contractual term)';
COMMENT ON COLUMN mortgage_agreement.is_recourse IS 'Whether loan is recourse (true) or non-recourse (false)';
COMMENT ON COLUMN mortgage_agreement.allows_early_repayment IS 'Whether early repayment is allowed per contract';
COMMENT ON COLUMN mortgage_agreement.early_repayment_penalty_rate IS 'Contractual penalty rate for early repayment (percentage)';
COMMENT ON COLUMN mortgage_agreement.early_repayment_penalty_period_months IS 'Period during which early repayment penalty applies (in months)';
COMMENT ON COLUMN mortgage_agreement.allows_partial_prepayment IS 'Whether partial prepayments are allowed per contract';
COMMENT ON COLUMN mortgage_agreement.partial_prepayment_min_amount IS 'Contractual minimum amount for partial prepayment';
COMMENT ON COLUMN mortgage_agreement.partial_prepayment_max_per_year IS 'Contractual maximum prepayment amount per year';
COMMENT ON COLUMN mortgage_agreement.subrogation_allowed IS 'Whether mortgage can be transferred to another lender (subrogation)';
COMMENT ON COLUMN mortgage_agreement.subrogation_fee IS 'Contractual fee for mortgage subrogation';
COMMENT ON COLUMN mortgage_agreement.agreement_signed_date IS 'Date the agreement was signed';
COMMENT ON COLUMN mortgage_agreement.agreement_effective_date IS 'Date the agreement becomes effective';

-- ========================================================================
-- INDEXES
-- ========================================================================

CREATE INDEX idx_mortgage_agreement_application_id ON mortgage_agreement(application_id);
CREATE INDEX idx_mortgage_agreement_servicing_case_id ON mortgage_agreement(servicing_case_id);
CREATE INDEX idx_mortgage_agreement_proposed_offer_id ON mortgage_agreement(proposed_offer_id);
CREATE INDEX idx_mortgage_agreement_mortgage_type ON mortgage_agreement(mortgage_type);
CREATE INDEX idx_mortgage_agreement_lien_position ON mortgage_agreement(lien_position);
CREATE INDEX idx_mortgage_agreement_signed_date ON mortgage_agreement(agreement_signed_date);
CREATE INDEX idx_mortgage_agreement_effective_date ON mortgage_agreement(agreement_effective_date);
CREATE INDEX idx_mortgage_agreement_status ON mortgage_agreement(agreement_status);

