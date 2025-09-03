package com.firefly.core.lending.mortgages.interfaces.dtos.appraisal.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.appraisal.v1.AppraisalTypeEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.appraisal.v1.LocationRatingEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.property.v1.PropertyConditionEnum;
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
public class MortgageAppraisalDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID appraisalId;

    @FilterableId
    @NotNull(message = "Property ID is required")
    private UUID propertyId;

    @FilterableId
    @NotNull(message = "Mortgage application ID is required")
    private UUID mortgageApplicationId;

    @NotBlank(message = "Appraiser name is required")
    @Size(max = 100, message = "Appraiser name cannot exceed 100 characters")
    private String appraiserName;

    @NotBlank(message = "License number is required")
    @Size(max = 50, message = "License number cannot exceed 50 characters")
    private String licenseNumber;

    @NotNull(message = "Appraisal type is required")
    private AppraisalTypeEnum appraisalType;

    @NotNull(message = "Market value is required")
    @DecimalMin(value = "0.01", message = "Market value must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Market value cannot exceed 999,999,999.99")
    private BigDecimal marketValue;

    @DecimalMin(value = "0.00", message = "Rental value cannot be negative")
    @DecimalMax(value = "999999999.99", message = "Rental value cannot exceed 999,999,999.99")
    private BigDecimal rentalValue;

    @DecimalMin(value = "0.00", message = "Replacement cost cannot be negative")
    @DecimalMax(value = "999999999.99", message = "Replacement cost cannot exceed 999,999,999.99")
    private BigDecimal replacementCost;

    @DecimalMin(value = "0.00", message = "Land value cannot be negative")
    @DecimalMax(value = "999999999.99", message = "Land value cannot exceed 999,999,999.99")
    private BigDecimal landValue;

    @DecimalMin(value = "0.00", message = "Building value cannot be negative")
    @DecimalMax(value = "999999999.99", message = "Building value cannot exceed 999,999,999.99")
    private BigDecimal buildingValue;
    @NotNull(message = "Property condition is required")
    private PropertyConditionEnum propertyCondition;

    @NotNull(message = "Location rating is required")
    private LocationRatingEnum locationRating;

    @Size(max = 5000, message = "Comparable properties cannot exceed 5000 characters")
    private String comparableProperties; // JSON array of comparables

    @NotNull(message = "Appraisal date is required")
    @PastOrPresent(message = "Appraisal date cannot be in the future")
    private LocalDate appraisalDate;

    @Future(message = "Expiry date must be in the future")
    private LocalDate expiryDate;

    @NotNull(message = "Requires repairs flag is required")
    private Boolean requiresRepairs;

    @Size(max = 2000, message = "Required repairs cannot exceed 2000 characters")
    private String requiredRepairs;

    @DecimalMin(value = "0.00", message = "Repair cost cannot be negative")
    @DecimalMax(value = "999999999.99", message = "Repair cost cannot exceed 999,999,999.99")
    private BigDecimal repairCost;

    @Size(max = 2000, message = "Assumptions cannot exceed 2000 characters")
    private String assumptions;

    @Size(max = 2000, message = "Limitations cannot exceed 2000 characters")
    private String limitations;

    @Size(max = 1000, message = "Methodology cannot exceed 1000 characters")
    private String methodology;

    @Size(max = 2000, message = "Comments cannot exceed 2000 characters")
    private String comments;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

