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


package com.firefly.core.lending.mortgages.interfaces.dtos.record.v1;

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
public class MortgagePaymentRecordDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID paymentRecordId;

    @FilterableId
    @NotNull(message = "Mortgage contract ID is required")
    private UUID mortgageContractId;

    @FilterableId
    @NotNull(message = "Payment schedule ID is required")
    private UUID paymentScheduleId;  // link to MortgagePaymentSchedule

    @FilterableId
    @NotNull(message = "Transaction ID is required")
    private UUID transactionId;       // external Payment domain reference

    @NotNull(message = "Payment amount is required")
    @DecimalMin(value = "0.01", message = "Payment amount must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Payment amount cannot exceed 999,999.99")
    private BigDecimal paymentAmount;

    @NotNull(message = "Payment date is required")
    @PastOrPresent(message = "Payment date cannot be in the future")
    private LocalDate paymentDate;

    @NotNull(message = "Partial payment flag is required")
    private Boolean isPartialPayment;

    @Size(max = 500, message = "Note cannot exceed 500 characters")
    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

