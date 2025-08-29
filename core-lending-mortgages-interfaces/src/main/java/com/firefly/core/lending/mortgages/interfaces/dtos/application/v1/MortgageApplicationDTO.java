package com.firefly.core.lending.mortgages.interfaces.dtos.application.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.application.v1.*;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageApplicationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long mortgageApplicationId;

    @FilterableId
    private Long applicantId;

    @FilterableId
    private Long coApplicantId;

    @FilterableId
    private Long propertyId;

    @FilterableId
    private Long productId;

    private ApplicationStatusEnum applicationStatus;
    private ApplicationChannelEnum applicationChannel;
    private BigDecimal requestedAmount;
    private Integer requestedTermMonths;
    private BigDecimal downPayment;
    private BigDecimal monthlyIncome;
    private BigDecimal monthlyExpenses;
    private EmploymentTypeEnum employmentType;
    private ResidenceTypeEnum residenceType;
    private Integer yearsAtCurrentJob;
    private Integer yearsAtCurrentAddress;
    private PurposeEnum purpose;
    private Boolean existingCustomer;
    private String remarks;
    private String assignedTo;
    private LocalDateTime submissionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}