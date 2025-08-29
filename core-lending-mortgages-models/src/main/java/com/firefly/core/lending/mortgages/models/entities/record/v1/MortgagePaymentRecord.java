package com.firefly.core.lending.mortgages.models.entities.record.v1;

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
@Table("mortgage_payment_record")
public class MortgagePaymentRecord {

    @Id
    @Column("payment_record_id")
    private Long paymentRecordId;

    @Column("mortgage_contract_id")
    private Long mortgageContractId;

    @Column("payment_schedule_id")
    private Long paymentScheduleId; // optional link to schedule

    @Column("transaction_id")
    private Long transactionId;     // external Payment domain reference

    @Column("payment_amount")
    private BigDecimal paymentAmount;

    @Column("payment_date")
    private LocalDate paymentDate;

    @Column("is_partial_payment")
    private Boolean isPartialPayment;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}