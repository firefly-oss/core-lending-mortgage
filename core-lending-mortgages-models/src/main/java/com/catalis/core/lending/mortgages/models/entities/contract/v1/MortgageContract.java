package com.catalis.core.lending.mortgages.models.entities.contract.v1;

import com.catalis.core.lending.mortgages.interfaces.enums.contract.v1.ContractStatusEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.contract.v1.RateTypeEnum;
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
@Table("mortgage_contract")
public class MortgageContract {

    @Id
    @Column("mortgage_contract_id")
    private Long mortgageContractId;

    @Column("mortgage_application_id")
    private Long mortgageApplicationId;

    @Column("property_id")
    private Long propertyId;

    @Column("contract_number")
    private String contractNumber;

    @Column("contract_status")
    private ContractStatusEnum contractStatus;

    @Column("loan_amount")
    private BigDecimal loanAmount;

    @Column("interest_rate")
    private BigDecimal interestRate;

    @Column("rate_type")
    private RateTypeEnum rateType;

    @Column("reference_rate")
    private String referenceRate;

    @Column("margin_rate")
    private BigDecimal marginRate;

    @Column("term_months")
    private Integer termMonths;

    @Column("start_date")
    private LocalDate startDate;

    @Column("maturity_date")
    private LocalDate maturityDate;

    @Column("monthly_payment")
    private BigDecimal monthlyPayment;

    @Column("early_repayment_fee")
    private BigDecimal earlyRepaymentFee;

    @Column("assumable")
    private Boolean assumable;

    @Column("tax_rate")
    private BigDecimal taxRate;

    @Column("special_conditions")
    private String specialConditions; // JSON

    @Column("notary_reference")
    private String notaryReference;

    @Column("signing_date")
    private LocalDateTime signingDate;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}