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


package com.firefly.core.lending.mortgages.interfaces.dtos.disbursement.v1;

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
public class MortgageDisbursementDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID mortgageDisbursementId;

    @FilterableId
    @NotNull(message = "Mortgage contract ID is required")
    private UUID mortgageContractId;

    @NotNull(message = "Disbursement amount is required")
    @DecimalMin(value = "0.01", message = "Disbursement amount must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Disbursement amount cannot exceed 999,999,999.99")
    private BigDecimal disbursementAmount;

    @NotNull(message = "Disbursement date is required")
    @PastOrPresent(message = "Disbursement date cannot be in the future")
    private LocalDate disbursementDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}