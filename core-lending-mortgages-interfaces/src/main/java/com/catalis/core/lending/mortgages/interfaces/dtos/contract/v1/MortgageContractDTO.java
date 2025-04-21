package com.catalis.core.lending.mortgages.interfaces.dtos.contract.v1;

import com.catalis.core.lending.mortgages.interfaces.enums.contract.v1.ContractStatusEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.contract.v1.RateTypeEnum;
import com.catalis.core.utils.annotations.FilterableId;
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
public class MortgageContractDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long mortgageContractId;

    @FilterableId
    private Long mortgageApplicationId;

    @FilterableId
    private Long propertyId;

    @FilterableId
    private String contractNumber;

    private ContractStatusEnum contractStatus;
    private BigDecimal loanAmount;
    private BigDecimal interestRate;
    private RateTypeEnum rateType;
    private String referenceRate;
    private BigDecimal marginRate;
    private Integer termMonths;
    private LocalDate startDate;
    private LocalDate maturityDate;
    private BigDecimal monthlyPayment;
    private BigDecimal earlyRepaymentFee;
    private Boolean assumable;
    private BigDecimal taxRate;
    private String specialConditions; // JSON format
    private String notaryReference;
    private LocalDateTime signingDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

