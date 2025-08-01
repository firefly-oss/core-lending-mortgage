package com.catalis.core.lending.mortgages.models.entities.schedule.v1;

import com.catalis.core.lending.mortgages.interfaces.enums.schedule.v1.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("mortgage_payment_schedule")
public class MortgagePaymentSchedule {

    @Id
    @Column("schedule_id")
    private Long scheduleId;

    @Column("mortgage_contract_id")
    private Long mortgageContractId;

    @Column("installment_number")
    private Integer installmentNumber;

    @Column("due_date")
    private LocalDate dueDate;

    @Column("principal_amount")
    private BigDecimal principalAmount;

    @Column("interest_amount")
    private BigDecimal interestAmount;

    @Column("fee_amount")
    private BigDecimal feeAmount;

    @Column("escrow_amount")
    private BigDecimal escrowAmount;

    @Column("total_amount")
    private BigDecimal totalAmount;

    @Column("payment_status")
    private PaymentStatusEnum paymentStatus;

    @Column("paid_date")
    private LocalDate paidDate;

    @Column("outstanding_balance")
    private BigDecimal outstandingBalance;

    @Column("late_fee_amount")
    private BigDecimal lateFeeAmount;

    @Column("days_late")
    private Integer daysLate;

    @Column("payment_breakdown")
    private String paymentBreakdown; // JSON

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}