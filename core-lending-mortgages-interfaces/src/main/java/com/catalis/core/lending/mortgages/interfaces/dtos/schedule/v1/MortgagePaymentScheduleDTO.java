package com.catalis.core.lending.mortgages.interfaces.dtos.schedule.v1;

import com.catalis.core.lending.mortgages.interfaces.enums.schedule.v1.PaymentStatusEnum;
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
public class MortgagePaymentScheduleDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long scheduleId;

    @FilterableId
    private Long mortgageContractId;

    private Integer installmentNumber;
    private LocalDate dueDate;
    private BigDecimal principalAmount;
    private BigDecimal interestAmount;
    private BigDecimal feeAmount;
    private BigDecimal escrowAmount;
    private BigDecimal totalAmount;
    private PaymentStatusEnum paymentStatus;
    private LocalDate paidDate;
    private BigDecimal outstandingBalance;
    private BigDecimal lateFeeAmount;
    private Integer daysLate;
    private String paymentBreakdown; // JSON detail
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

