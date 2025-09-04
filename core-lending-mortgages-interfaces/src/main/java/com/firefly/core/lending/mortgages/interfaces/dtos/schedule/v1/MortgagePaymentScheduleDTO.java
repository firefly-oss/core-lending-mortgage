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


package com.firefly.core.lending.mortgages.interfaces.dtos.schedule.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.schedule.v1.PaymentStatusEnum;
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
public class MortgagePaymentScheduleDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID scheduleId;

    @FilterableId
    @NotNull(message = "Mortgage contract ID is required")
    private UUID mortgageContractId;

    @NotNull(message = "Installment number is required")
    @Min(value = 1, message = "Installment number must be at least 1")
    @Max(value = 480, message = "Installment number cannot exceed 480")
    private Integer installmentNumber;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    @NotNull(message = "Principal amount is required")
    @DecimalMin(value = "0.00", message = "Principal amount cannot be negative")
    @DecimalMax(value = "999999.99", message = "Principal amount cannot exceed 999,999.99")
    private BigDecimal principalAmount;

    @NotNull(message = "Interest amount is required")
    @DecimalMin(value = "0.00", message = "Interest amount cannot be negative")
    @DecimalMax(value = "999999.99", message = "Interest amount cannot exceed 999,999.99")
    private BigDecimal interestAmount;

    @DecimalMin(value = "0.00", message = "Fee amount cannot be negative")
    @DecimalMax(value = "99999.99", message = "Fee amount cannot exceed 99,999.99")
    private BigDecimal feeAmount;

    @DecimalMin(value = "0.00", message = "Escrow amount cannot be negative")
    @DecimalMax(value = "99999.99", message = "Escrow amount cannot exceed 99,999.99")
    private BigDecimal escrowAmount;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.01", message = "Total amount must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Total amount cannot exceed 999,999.99")
    private BigDecimal totalAmount;

    @NotNull(message = "Payment status is required")
    private PaymentStatusEnum paymentStatus;

    @PastOrPresent(message = "Paid date cannot be in the future")
    private LocalDate paidDate;

    @NotNull(message = "Outstanding balance is required")
    @DecimalMin(value = "0.00", message = "Outstanding balance cannot be negative")
    @DecimalMax(value = "999999999.99", message = "Outstanding balance cannot exceed 999,999,999.99")
    private BigDecimal outstandingBalance;

    @DecimalMin(value = "0.00", message = "Late fee amount cannot be negative")
    @DecimalMax(value = "99999.99", message = "Late fee amount cannot exceed 99,999.99")
    private BigDecimal lateFeeAmount;

    @Min(value = 0, message = "Days late cannot be negative")
    @Max(value = 9999, message = "Days late cannot exceed 9999")
    private Integer daysLate;

    @Size(max = 2000, message = "Payment breakdown cannot exceed 2000 characters")
    private String paymentBreakdown; // JSON detail

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

