package com.catalis.core.lending.mortgages.interfaces.dtos.appraisal.v1;

import com.catalis.core.lending.mortgages.interfaces.enums.appraisal.v1.AppraisalTypeEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.appraisal.v1.LocationRatingEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.property.v1.PropertyConditionEnum;
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
public class MortgageAppraisalDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long appraisalId;

    @FilterableId
    private Long propertyId;

    @FilterableId
    private Long mortgageApplicationId;

    private String appraiserName;

    private String licenseNumber;
    private AppraisalTypeEnum appraisalType;
    private BigDecimal marketValue;
    private BigDecimal rentalValue;
    private BigDecimal replacementCost;
    private BigDecimal landValue;
    private BigDecimal buildingValue;
    private PropertyConditionEnum propertyCondition;
    private LocationRatingEnum locationRating;
    private String comparableProperties; // JSON array of comparables
    private LocalDate appraisalDate;
    private LocalDate expiryDate;
    private Boolean requiresRepairs;
    private String requiredRepairs;
    private BigDecimal repairCost;
    private String assumptions;
    private String limitations;
    private String methodology;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

