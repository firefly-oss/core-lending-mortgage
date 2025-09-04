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


package com.firefly.core.lending.mortgages.models.entities.application.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.application.v1.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("mortgage_application")
public class MortgageApplication {

    @Id
    @Column("mortgage_application_id")
    private UUID mortgageApplicationId;

    @Column("applicant_id")
    private UUID applicantId;  // external reference (Customer domain)

    @Column("co_applicant_id")
    private UUID coApplicantId;  // optional second applicant external reference (Customer domain)

    @Column("property_id")
    private UUID propertyId;   // references mortgage_property

    @Column("product_id")
    private UUID productId;    // references a mortgage product setup

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