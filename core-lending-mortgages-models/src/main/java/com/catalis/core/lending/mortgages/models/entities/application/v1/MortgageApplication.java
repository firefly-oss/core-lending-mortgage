package com.catalis.core.lending.mortgages.models.entities.application.v1;

import com.catalis.core.lending.mortgages.interfaces.enums.application.v1.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("mortgage_application")
public class MortgageApplication {

    @Id
    @Column("mortgage_application_id")
    private Long mortgageApplicationId;

    @Column("applicant_id")
    private Long applicantId;  // external reference (Customer domain)

    @Column("co_applicant_id")
    private Long coApplicantId;  // optional second applicant external reference (Customer domain)

    @Column("property_id")
    private Long propertyId;   // references mortgage_property

    @Column("product_id")
    private Long productId;    // references a mortgage product setup

    @Column("application_status")
    private ApplicationStatusEnum applicationStatus;

    @Column("application_channel")
    private ApplicationChannelEnum applicationChannel;

    @Column("requested_amount")
    private BigDecimal requestedAmount;

    @Column("requested_term_months")
    private Integer requestedTermMonths;

    @Column("down_payment")
    private BigDecimal downPayment;

    @Column("monthly_income")
    private BigDecimal monthlyIncome;

    @Column("monthly_expenses")
    private BigDecimal monthlyExpenses;

    @Column("employment_type")
    private EmploymentTypeEnum employmentType;

    @Column("residence_type")
    private ResidenceTypeEnum residenceType;

    @Column("years_at_current_job")
    private Integer yearsAtCurrentJob;

    @Column("years_at_current_address")
    private Integer yearsAtCurrentAddress;

    @Column("purpose")
    private PurposeEnum purpose;

    @Column("existing_customer")
    private Boolean existingCustomer;

    @Column("remarks")
    private String remarks;

    @Column("assigned_to")
    private String assignedTo;

    @Column("submission_date")
    private LocalDateTime submissionDate;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}