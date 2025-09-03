package com.firefly.core.lending.mortgages.models.entities.disbursement.v1;

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
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("mortgage_disbursement")
public class MortgageDisbursement {

    @Id
    @Column("disbursement_id")
    private UUID disbursementId;

    @Column("mortgage_contract_id")
    private UUID mortgageContractId;

    @Column("mortgage_application_id")
    private UUID mortgageApplicationId;

    @Column("disbursement_amount")
    private BigDecimal disbursementAmount;

    @Column("disbursement_date")
    private LocalDate disbursementDate;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
