package com.catalis.core.lending.mortgages.interfaces.dtos.property.v1;

import com.catalis.core.lending.mortgages.interfaces.enums.property.v1.EnergyRatingEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.property.v1.PropertyStatusEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.property.v1.PropertyTypeEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.property.v1.PropertyUseEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgagePropertyDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long propertyId;

    private PropertyTypeEnum propertyType;
    private PropertyStatusEnum propertyStatus;
    private PropertyUseEnum propertyUse;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String countryCode;
    private BigDecimal landArea;
    private BigDecimal builtArea;
    private Integer constructionYear;
    private Integer renovationYear;
    private String titleNumber;
    private String cadastralReference;
    private String legalDescription;
    private Integer totalRooms;
    private Integer totalBedrooms;
    private Integer totalBathrooms;
    private Boolean hasParking;
    private Integer parkingSpaces;
    private Boolean hasStorage;
    private BigDecimal storageArea;
    private Boolean hasElevator;
    private Integer floorNumber;
    private EnergyRatingEnum energyRating;
    private String restrictions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
