package com.catalis.core.lending.mortgages.interfaces.dtos.insurance.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.mortgages.interfaces.enums.insurance.v1.InsuranceTypeEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.insurance.v1.PremiumFrequencyEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageInsuranceDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long insuranceId;

    @FilterableId
    private Long mortgageContractId;

    private InsuranceTypeEnum insuranceType;
    private String policyNumber;
    private String providerName;
    private String providerCode;
    private BigDecimal coverageAmount;
    private BigDecimal deductibleAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal annualPremium;
    private PremiumFrequencyEnum premiumFrequency;
    private BigDecimal premiumAmount;
    private Boolean bankBeneficiary;
    private Boolean isActive;
    private LocalDate lastPaymentDate;
    private LocalDate nextPaymentDate;
    private String coverageDetails; // JSON
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

