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


package com.firefly.core.lending.mortgages.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.core.lending.mortgages.interfaces.enums.AgreementStatusEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.LienPositionEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.MortgageTypeEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.RateTypeEnum;
import org.fireflyframework.utils.annotations.FilterableId;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageAgreementDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID mortgageAgreementId;

    // ========================================================================
    // External References
    // ========================================================================

    @FilterableId
    @NotNull(message = "Application ID is required")
    private UUID applicationId;

    @FilterableId
    private UUID servicingCaseId;

    @FilterableId
    private UUID proposedOfferId;

    // ========================================================================
    // Mortgage-Specific Agreement Terms
    // ========================================================================

    @NotNull(message = "Agreement status is required")
    private AgreementStatusEnum agreementStatus;

    @NotNull(message = "Mortgage type is required")
    private MortgageTypeEnum mortgageType;

    private LienPositionEnum lienPosition;

    // ========================================================================
    // Interest Rate Terms (Contractual - what was AGREED)
    // ========================================================================

    @NotNull(message = "Rate type is required")
    private RateTypeEnum rateType;

    @DecimalMin(value = "0.00", message = "Initial interest rate cannot be negative")
    @DecimalMax(value = "100.00", message = "Initial interest rate cannot exceed 100%")
    private BigDecimal initialInterestRate;

    @Size(max = 50, message = "Reference rate cannot exceed 50 characters")
    private String referenceRate;

    @DecimalMin(value = "-10.00", message = "Margin rate cannot be less than -10%")
    @DecimalMax(value = "50.00", message = "Margin rate cannot exceed 50%")
    private BigDecimal marginRate;

    @Min(value = 0, message = "Fixed rate period cannot be negative")
    @Max(value = 480, message = "Fixed rate period cannot exceed 480 months")
    private Integer fixedRatePeriodMonths;

    @DecimalMin(value = "0.00", message = "Rate cap cannot be negative")
    @DecimalMax(value = "100.00", message = "Rate cap cannot exceed 100%")
    private BigDecimal rateCap;

    @DecimalMin(value = "0.00", message = "Rate floor cannot be negative")
    @DecimalMax(value = "100.00", message = "Rate floor cannot exceed 100%")
    private BigDecimal rateFloor;

    @DecimalMin(value = "0.00", message = "Periodic rate cap cannot be negative")
    @DecimalMax(value = "100.00", message = "Periodic rate cap cannot exceed 100%")
    private BigDecimal periodicRateCap;

    // ========================================================================
    // Islamic Finance Specific
    // ========================================================================

    @DecimalMin(value = "0.00", message = "Profit rate cannot be negative")
    @DecimalMax(value = "100.00", message = "Profit rate cannot exceed 100%")
    private BigDecimal profitRate;

    @DecimalMin(value = "0.00", message = "Rental rate cannot be negative")
    @DecimalMax(value = "100.00", message = "Rental rate cannot exceed 100%")
    private BigDecimal rentalRate;

    @DecimalMin(value = "0.00", message = "Bank ownership percentage cannot be negative")
    @DecimalMax(value = "100.00", message = "Bank ownership percentage cannot exceed 100%")
    private BigDecimal bankOwnershipPercentage;

    @DecimalMin(value = "0.00", message = "Purchase price cannot be negative")
    private BigDecimal purchasePrice;

    @DecimalMin(value = "0.00", message = "Markup amount cannot be negative")
    private BigDecimal markupAmount;

    // ========================================================================
    // Mortgage Legal and Regulatory
    // ========================================================================

    @Size(max = 200, message = "Notary name cannot exceed 200 characters")
    private String notaryName;

    @Size(max = 100, message = "Notary registration number cannot exceed 100 characters")
    private String notaryRegistrationNumber;

    @Size(max = 100, message = "Deed number cannot exceed 100 characters")
    private String deedNumber;

    private LocalDate deedDate;

    @Size(max = 200, message = "Registry office cannot exceed 200 characters")
    private String registryOffice;

    @Size(max = 50, message = "Registry volume cannot exceed 50 characters")
    private String registryVolume;

    @Size(max = 50, message = "Registry book cannot exceed 50 characters")
    private String registryBook;

    @Size(max = 50, message = "Registry folio cannot exceed 50 characters")
    private String registryFolio;

    private LocalDate registryInscriptionDate;

    // ========================================================================
    // Mortgage-Specific Contractual Features (What's ALLOWED per contract)
    // ========================================================================

    private Boolean isAssumable;
    private Boolean isPortable;
    private Boolean isRecourse;
    private Boolean allowsEarlyRepayment;

    @DecimalMin(value = "0.00", message = "Early repayment penalty rate cannot be negative")
    @DecimalMax(value = "100.00", message = "Early repayment penalty rate cannot exceed 100%")
    private BigDecimal earlyRepaymentPenaltyRate;

    @Min(value = 0, message = "Early repayment penalty period cannot be negative")
    @Max(value = 480, message = "Early repayment penalty period cannot exceed 480 months")
    private Integer earlyRepaymentPenaltyPeriodMonths;

    private Boolean allowsPartialPrepayment;

    @DecimalMin(value = "0.00", message = "Partial prepayment minimum amount cannot be negative")
    private BigDecimal partialPrepaymentMinAmount;

    @DecimalMin(value = "0.00", message = "Partial prepayment max per year cannot be negative")
    private BigDecimal partialPrepaymentMaxPerYear;

    private Boolean subrogationAllowed;

    @DecimalMin(value = "0.00", message = "Subrogation fee cannot be negative")
    private BigDecimal subrogationFee;

    // ========================================================================
    // Audit and Lifecycle
    // ========================================================================

    private LocalDate agreementSignedDate;
    private LocalDate agreementEffectiveDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createdBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String updatedBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}

