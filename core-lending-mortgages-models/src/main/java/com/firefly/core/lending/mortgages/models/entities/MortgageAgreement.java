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


package com.firefly.core.lending.mortgages.models.entities;

import com.firefly.core.lending.mortgages.interfaces.enums.AgreementStatusEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.LienPositionEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.MortgageTypeEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.RateTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("mortgage_agreement")
public class MortgageAgreement {

    @Id
    @Column("mortgage_agreement_id")
    private UUID mortgageAgreementId;

    // ========================================================================
    // External References (to other microservices)
    // ========================================================================

    @Column("application_id")
    private UUID applicationId;  // Reference to LoanApplication (Loan Origination)

    @Column("servicing_case_id")
    private UUID servicingCaseId;  // Reference to LoanServicingCase (Loan Servicing)

    @Column("proposed_offer_id")
    private UUID proposedOfferId;  // Reference to ProposedOffer (Loan Origination)

    // ========================================================================
    // Mortgage-Specific Agreement Terms
    // ========================================================================

    @Column("agreement_status")
    private AgreementStatusEnum agreementStatus;

    @Column("mortgage_type")
    private MortgageTypeEnum mortgageType;  // Type of mortgage (conventional, Islamic, reverse, etc.)

    @Column("lien_position")
    private LienPositionEnum lienPosition;  // First, second, third lien

    // ========================================================================
    // Interest Rate Terms (Contractual - what was AGREED)
    // ========================================================================

    @Column("rate_type")
    private RateTypeEnum rateType;  // FIXED, VARIABLE, HYBRID

    @Column("initial_interest_rate")
    private BigDecimal initialInterestRate;  // Initial rate at contract start

    @Column("reference_rate")
    private String referenceRate;  // e.g., "EURIBOR_12M", "LIBOR_3M", "SOFR", "PRIME" (for variable/hybrid)

    @Column("margin_rate")
    private BigDecimal marginRate;  // Margin/spread added to reference rate (for variable/hybrid)

    @Column("fixed_rate_period_months")
    private Integer fixedRatePeriodMonths;  // For hybrid mortgages - initial fixed period

    @Column("rate_cap")
    private BigDecimal rateCap;  // Maximum interest rate cap (contractual limit)

    @Column("rate_floor")
    private BigDecimal rateFloor;  // Minimum interest rate floor (contractual limit)

    @Column("periodic_rate_cap")
    private BigDecimal periodicRateCap;  // Maximum rate change per adjustment period (contractual limit)

    // ========================================================================
    // Islamic Finance Specific (if applicable)
    // ========================================================================

    @Column("profit_rate")
    private BigDecimal profitRate;  // Profit rate for Islamic mortgages

    @Column("rental_rate")
    private BigDecimal rentalRate;  // Rental rate for Ijara mortgages

    @Column("bank_ownership_percentage")
    private BigDecimal bankOwnershipPercentage;  // For Musharaka/diminishing Musharaka

    @Column("purchase_price")
    private BigDecimal purchasePrice;  // Purchase price for Murabaha

    @Column("markup_amount")
    private BigDecimal markupAmount;  // Markup for Murabaha

    // ========================================================================
    // Mortgage Legal and Regulatory
    // ========================================================================

    @Column("notary_name")
    private String notaryName;  // Notary who formalized the contract

    @Column("notary_registration_number")
    private String notaryRegistrationNumber;  // Notary's official registration

    @Column("deed_number")
    private String deedNumber;  // Public deed number

    @Column("deed_date")
    private LocalDate deedDate;  // Date of deed execution

    @Column("registry_office")
    private String registryOffice;  // Property registry office

    @Column("registry_volume")
    private String registryVolume;  // Registry volume number

    @Column("registry_book")
    private String registryBook;  // Registry book number

    @Column("registry_folio")
    private String registryFolio;  // Registry folio number

    @Column("registry_inscription_date")
    private LocalDate registryInscriptionDate;  // Date registered in property registry

    // ========================================================================
    // Mortgage-Specific Contractual Features (What's ALLOWED per contract)
    // ========================================================================

    @Column("is_assumable")
    private Boolean isAssumable;  // Can mortgage be assumed by new buyer

    @Column("is_portable")
    private Boolean isPortable;  // Can be transferred to new property

    @Column("is_recourse")
    private Boolean isRecourse;  // Recourse vs non-recourse loan

    @Column("allows_early_repayment")
    private Boolean allowsEarlyRepayment;  // Early repayment allowed per contract

    @Column("early_repayment_penalty_rate")
    private BigDecimal earlyRepaymentPenaltyRate;  // Contractual penalty % for early repayment

    @Column("early_repayment_penalty_period_months")
    private Integer earlyRepaymentPenaltyPeriodMonths;  // Contractual period during which penalty applies

    @Column("allows_partial_prepayment")
    private Boolean allowsPartialPrepayment;  // Partial prepayments allowed per contract

    @Column("partial_prepayment_min_amount")
    private BigDecimal partialPrepaymentMinAmount;  // Contractual minimum amount for partial prepayment

    @Column("partial_prepayment_max_per_year")
    private BigDecimal partialPrepaymentMaxPerYear;  // Contractual maximum prepayment per year

    @Column("subrogation_allowed")
    private Boolean subrogationAllowed;  // Can be transferred to another lender per contract

    @Column("subrogation_fee")
    private BigDecimal subrogationFee;  // Contractual fee for subrogation

    // ========================================================================
    // Audit and Lifecycle
    // ========================================================================

    @Column("agreement_signed_date")
    private LocalDate agreementSignedDate;  // Date agreement was signed

    @Column("agreement_effective_date")
    private LocalDate agreementEffectiveDate;  // Date agreement becomes effective

    @Column("created_by")
    private String createdBy;  // User who created the agreement

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_by")
    private String updatedBy;  // User who last updated

    @Column("updated_at")
    private LocalDateTime updatedAt;
}