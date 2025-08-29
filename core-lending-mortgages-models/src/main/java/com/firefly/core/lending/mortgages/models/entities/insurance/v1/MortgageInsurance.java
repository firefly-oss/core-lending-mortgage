package com.firefly.core.lending.mortgages.models.entities.insurance.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.insurance.v1.InsuranceTypeEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.insurance.v1.PremiumFrequencyEnum;
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
@Table("mortgage_insurance")
public class MortgageInsurance {

    @Id
    @Column("insurance_id")
    private Long insuranceId;

    @Column("mortgage_contract_id")
    private Long mortgageContractId;

    @Column("insurance_type")
    private InsuranceTypeEnum insuranceType;

    @Column("policy_number")
    private String policyNumber;

    @Column("provider_name")
    private String providerName;

    @Column("provider_code")
    private String providerCode;

    @Column("coverage_amount")
    private BigDecimal coverageAmount;

    @Column("deductible_amount")
    private BigDecimal deductibleAmount;

    @Column("start_date")
    private LocalDate startDate;

    @Column("end_date")
    private LocalDate endDate;

    @Column("annual_premium")
    private BigDecimal annualPremium;

    @Column("premium_frequency")
    private PremiumFrequencyEnum premiumFrequency;

    @Column("premium_amount")
    private BigDecimal premiumAmount;

    @Column("bank_beneficiary")
    private Boolean bankBeneficiary;

    @Column("is_active")
    private Boolean isActive;

    @Column("last_payment_date")
    private LocalDate lastPaymentDate;

    @Column("next_payment_date")
    private LocalDate nextPaymentDate;

    @Column("coverage_details")
    private String coverageDetails; // JSON

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}