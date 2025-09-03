package com.firefly.core.lending.mortgages.interfaces.dtos.insurance.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.insurance.v1.InsuranceTypeEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.insurance.v1.PremiumFrequencyEnum;
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
public class MortgageInsuranceDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID insuranceId;

    @FilterableId
    @NotNull(message = "Mortgage contract ID is required")
    private UUID mortgageContractId;

    @NotNull(message = "Insurance type is required")
    private InsuranceTypeEnum insuranceType;

    @NotBlank(message = "Policy number is required")
    @Size(max = 50, message = "Policy number cannot exceed 50 characters")
    private String policyNumber;

    @NotBlank(message = "Provider name is required")
    @Size(max = 100, message = "Provider name cannot exceed 100 characters")
    private String providerName;

    @Size(max = 20, message = "Provider code cannot exceed 20 characters")
    private String providerCode;

    @NotNull(message = "Coverage amount is required")
    @DecimalMin(value = "0.01", message = "Coverage amount must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Coverage amount cannot exceed 999,999,999.99")
    private BigDecimal coverageAmount;

    @DecimalMin(value = "0.00", message = "Deductible amount cannot be negative")
    @DecimalMax(value = "999999.99", message = "Deductible amount cannot exceed 999,999.99")
    private BigDecimal deductibleAmount;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @NotNull(message = "Annual premium is required")
    @DecimalMin(value = "0.01", message = "Annual premium must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Annual premium cannot exceed 999,999.99")
    private BigDecimal annualPremium;

    @NotNull(message = "Premium frequency is required")
    private PremiumFrequencyEnum premiumFrequency;

    @NotNull(message = "Premium amount is required")
    @DecimalMin(value = "0.01", message = "Premium amount must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Premium amount cannot exceed 999,999.99")
    private BigDecimal premiumAmount;
    @NotNull(message = "Bank beneficiary flag is required")
    private Boolean bankBeneficiary;

    @NotNull(message = "Active status is required")
    private Boolean isActive;

    @PastOrPresent(message = "Last payment date cannot be in the future")
    private LocalDate lastPaymentDate;

    private LocalDate nextPaymentDate;

    @Size(max = 2000, message = "Coverage details cannot exceed 2000 characters")
    private String coverageDetails; // JSON

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

