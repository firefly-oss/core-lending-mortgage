package com.firefly.core.lending.mortgages.models.entities.property.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.property.v1.EnergyRatingEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.property.v1.PropertyStatusEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.property.v1.PropertyTypeEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.property.v1.PropertyUseEnum;
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
@Table("mortgage_property")
public class MortgageProperty {

    @Id
    @Column("property_id")
    private UUID propertyId;

    @Column("property_type")
    private PropertyTypeEnum propertyType;

    @Column("property_status")
    private PropertyStatusEnum propertyStatus;

    @Column("property_use")
    private PropertyUseEnum propertyUse;

    @Column("address_line1")
    private String addressLine1;

    @Column("address_line2")
    private String addressLine2;

    @Column("city")
    private String city;

    @Column("state")
    private String state;

    @Column("postal_code")
    private String postalCode;

    @Column("country_code")
    private String countryCode;

    @Column("land_area")
    private BigDecimal landArea;

    @Column("built_area")
    private BigDecimal builtArea;

    @Column("construction_year")
    private Integer constructionYear;

    @Column("renovation_year")
    private Integer renovationYear;

    @Column("title_number")
    private String titleNumber;

    @Column("cadastral_reference")
    private String cadastralReference;

    @Column("legal_description")
    private String legalDescription;

    @Column("total_rooms")
    private Integer totalRooms;

    @Column("total_bedrooms")
    private Integer totalBedrooms;

    @Column("total_bathrooms")
    private Integer totalBathrooms;

    @Column("has_parking")
    private Boolean hasParking;

    @Column("parking_spaces")
    private Integer parkingSpaces;

    @Column("has_storage")
    private Boolean hasStorage;

    @Column("storage_area")
    private BigDecimal storageArea;

    @Column("has_elevator")
    private Boolean hasElevator;

    @Column("floor_number")
    private Integer floorNumber;

    @Column("energy_rating")
    private EnergyRatingEnum energyRating;

    @Column("restrictions")
    private String restrictions;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}