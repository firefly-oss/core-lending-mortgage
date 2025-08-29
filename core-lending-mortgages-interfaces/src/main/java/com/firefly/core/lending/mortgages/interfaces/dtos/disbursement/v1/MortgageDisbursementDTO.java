package com.firefly.core.lending.mortgages.interfaces.dtos.disbursement.v1;

import com.firefly.core.utils.annotations.FilterableId;
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
public class MortgageDisbursementDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long mortgageDisbursementId;

    @FilterableId
    private Long mortgageContractId;

    private BigDecimal disbursementAmount;
    private LocalDate disbursementDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}