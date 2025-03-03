package com.catalis.core.lending.mortgages.interfaces.dtos.record.v1;

import com.catalis.common.core.filters.FilterableId;
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
public class MortgagePaymentRecordDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long paymentRecordId;

    @FilterableId
    private Long mortgageContractId;

    @FilterableId
    private Long paymentScheduleId;  // link to MortgagePaymentSchedule

    @FilterableId
    private Long transactionId;       // external Payment domain reference

    private BigDecimal paymentAmount;
    private LocalDate paymentDate;
    private Boolean isPartialPayment;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

