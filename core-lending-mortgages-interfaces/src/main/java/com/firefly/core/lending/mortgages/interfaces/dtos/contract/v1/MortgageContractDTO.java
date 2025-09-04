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


package com.firefly.core.lending.mortgages.interfaces.dtos.contract.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.contract.v1.ContractStatusEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.contract.v1.RateTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageContractDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID mortgageContractId;

    @FilterableId
    @NotNull(message = "Mortgage application ID is required")
    private UUID mortgageApplicationId;

    @FilterableId
    @NotNull(message = "Property ID is required")
    private UUID propertyId;

    @FilterableId
    @NotBlank(message = "Contract number is required")
    @Size(max = 50, message = "Contract number cannot exceed 50 characters")
    private String contractNumber;

    @NotNull(message = "Contract status is required")
    private ContractStatusEnum contractStatus;

    @NotNull(message = "Loan amount is required")
    @DecimalMin(value = "0.01", message = "Loan amount must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Loan amount cannot exceed 999,999,999.99")
    private BigDecimal loanAmount;

    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.00", message = "Interest rate cannot be negative")
    @DecimalMax(value = "100.00", message = "Interest rate cannot exceed 100%")
    private BigDecimal interestRate;

    @NotNull(message = "Rate type is required")
    private RateTypeEnum rateType;

    @Size(max = 50, message = "Reference rate cannot exceed 50 characters")
    private String referenceRate;

    @DecimalMin(value = "-10.00", message = "Margin rate cannot be less than -10%")
    @DecimalMax(value = "50.00", message = "Margin rate cannot exceed 50%")
    private BigDecimal marginRate;

    @NotNull(message = "Term in months is required")
    @Min(value = 1, message = "Term must be at least 1 month")
    @Max(value = 480, message = "Term cannot exceed 480 months (40 years)")
    private Integer termMonths;
    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "Maturity date is required")
    @Future(message = "Maturity date must be in the future")
    private LocalDate maturityDate;

    @NotNull(message = "Monthly payment is required")
    @DecimalMin(value = "0.01", message = "Monthly payment must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Monthly payment cannot exceed 999,999.99")
    private BigDecimal monthlyPayment;

    @DecimalMin(value = "0.00", message = "Early repayment fee cannot be negative")
    @DecimalMax(value = "999999.99", message = "Early repayment fee cannot exceed 999,999.99")
    private BigDecimal earlyRepaymentFee;

    @NotNull(message = "Assumable flag is required")
    private Boolean assumable;

    @DecimalMin(value = "0.00", message = "Tax rate cannot be negative")
    @DecimalMax(value = "100.00", message = "Tax rate cannot exceed 100%")
    private BigDecimal taxRate;

    @Size(max = 5000, message = "Special conditions cannot exceed 5000 characters")
    private String specialConditions; // JSON format

    @Size(max = 100, message = "Notary reference cannot exceed 100 characters")
    private String notaryReference;

    private LocalDateTime signingDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

