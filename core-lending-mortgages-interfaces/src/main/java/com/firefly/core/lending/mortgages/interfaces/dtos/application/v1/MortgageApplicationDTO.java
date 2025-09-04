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


package com.firefly.core.lending.mortgages.interfaces.dtos.application.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.application.v1.*;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageApplicationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID mortgageApplicationId;

    @FilterableId
    @NotNull(message = "Applicant ID is required")
    private UUID applicantId;

    @FilterableId
    private UUID coApplicantId;

    @FilterableId
    @NotNull(message = "Property ID is required")
    private UUID propertyId;

    @FilterableId
    @NotNull(message = "Product ID is required")
    private UUID productId;

    @NotNull(message = "Application status is required")
    private ApplicationStatusEnum applicationStatus;

    @NotNull(message = "Application channel is required")
    private ApplicationChannelEnum applicationChannel;

    @NotNull(message = "Requested amount is required")
    @DecimalMin(value = "0.01", message = "Requested amount must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Requested amount cannot exceed 999,999,999.99")
    private BigDecimal requestedAmount;

    @NotNull(message = "Requested term in months is required")
    @Min(value = 1, message = "Term must be at least 1 month")
    @Max(value = 480, message = "Term cannot exceed 480 months (40 years)")
    private Integer requestedTermMonths;

    @NotNull(message = "Down payment is required")
    @DecimalMin(value = "0.00", message = "Down payment cannot be negative")
    private BigDecimal downPayment;

    @NotNull(message = "Monthly income is required")
    @DecimalMin(value = "0.01", message = "Monthly income must be greater than 0")
    private BigDecimal monthlyIncome;

    @NotNull(message = "Monthly expenses is required")
    @DecimalMin(value = "0.00", message = "Monthly expenses cannot be negative")
    private BigDecimal monthlyExpenses;
    @NotNull(message = "Employment type is required")
    private EmploymentTypeEnum employmentType;

    @NotNull(message = "Residence type is required")
    private ResidenceTypeEnum residenceType;

    @Min(value = 0, message = "Years at current job cannot be negative")
    @Max(value = 70, message = "Years at current job cannot exceed 70")
    private Integer yearsAtCurrentJob;

    @Min(value = 0, message = "Years at current address cannot be negative")
    @Max(value = 100, message = "Years at current address cannot exceed 100")
    private Integer yearsAtCurrentAddress;

    @NotNull(message = "Purpose is required")
    private PurposeEnum purpose;

    @NotNull(message = "Existing customer flag is required")
    private Boolean existingCustomer;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;

    @Size(max = 100, message = "Assigned to cannot exceed 100 characters")
    private String assignedTo;

    private LocalDateTime submissionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}