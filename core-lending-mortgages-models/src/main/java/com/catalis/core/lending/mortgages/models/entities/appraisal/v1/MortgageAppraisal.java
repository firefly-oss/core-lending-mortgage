package com.catalis.core.lending.mortgages.models.entities.appraisal.v1;

import com.catalis.core.lending.mortgages.interfaces.enums.appraisal.v1.AppraisalTypeEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.appraisal.v1.LocationRatingEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.property.v1.PropertyConditionEnum;
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
@Table("mortgage_appraisal")
public class MortgageAppraisal {

    @Id
    @Column("appraisal_id")
    private Long appraisalId;

    @Column("property_id")
    private Long propertyId;

    @Column("mortgage_application_id")
    private Long mortgageApplicationId;

    @Column("appraiser_name")
    private String appraiserName;

    @Column("license_number")
    private String licenseNumber;

    @Column("appraisal_type")
    private AppraisalTypeEnum appraisalType;

    @Column("market_value")
    private BigDecimal marketValue;

    @Column("rental_value")
    private BigDecimal rentalValue;

    @Column("replacement_cost")
    private BigDecimal replacementCost;

    @Column("land_value")
    private BigDecimal landValue;

    @Column("building_value")
    private BigDecimal buildingValue;

    @Column("property_condition")
    private PropertyConditionEnum propertyCondition;

    @Column("location_rating")
    private LocationRatingEnum locationRating;

    @Column("comparable_properties")
    private String comparableProperties; // JSON array

    @Column("appraisal_date")
    private LocalDate appraisalDate;

    @Column("expiry_date")
    private LocalDate expiryDate;

    @Column("requires_repairs")
    private Boolean requiresRepairs;

    @Column("required_repairs")
    private String requiredRepairs;

    @Column("repair_cost")
    private BigDecimal repairCost;

    @Column("assumptions")
    private String assumptions;

    @Column("limitations")
    private String limitations;

    @Column("methodology")
    private String methodology;

    @Column("comments")
    private String comments;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}