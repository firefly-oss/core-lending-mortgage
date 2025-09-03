package com.firefly.core.lending.mortgages.interfaces.dtos.property.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.property.v1.EnergyRatingEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.property.v1.PropertyStatusEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.property.v1.PropertyTypeEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.property.v1.PropertyUseEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgagePropertyDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID propertyId;

    @NotNull(message = "Property type is required")
    private PropertyTypeEnum propertyType;

    @NotNull(message = "Property status is required")
    private PropertyStatusEnum propertyStatus;

    @NotNull(message = "Property use is required")
    private PropertyUseEnum propertyUse;

    @NotBlank(message = "Address line 1 is required")
    @Size(max = 200, message = "Address line 1 cannot exceed 200 characters")
    private String addressLine1;

    @Size(max = 200, message = "Address line 2 cannot exceed 200 characters")
    private String addressLine2;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 100, message = "State cannot exceed 100 characters")
    private String state;

    @NotBlank(message = "Postal code is required")
    @Size(max = 20, message = "Postal code cannot exceed 20 characters")
    private String postalCode;

    @NotBlank(message = "Country code is required")
    @Size(min = 2, max = 3, message = "Country code must be 2-3 characters")
    private String countryCode;

    @DecimalMin(value = "0.01", message = "Land area must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Land area cannot exceed 999,999.99")
    private BigDecimal landArea;

    @DecimalMin(value = "0.01", message = "Built area must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Built area cannot exceed 999,999.99")
    private BigDecimal builtArea;

    @Min(value = 1800, message = "Construction year cannot be before 1800")
    @Max(value = 2100, message = "Construction year cannot be after 2100")
    private Integer constructionYear;

    @Min(value = 1800, message = "Renovation year cannot be before 1800")
    @Max(value = 2100, message = "Renovation year cannot be after 2100")
    private Integer renovationYear;
    @Size(max = 100, message = "Title number cannot exceed 100 characters")
    private String titleNumber;

    @Size(max = 100, message = "Cadastral reference cannot exceed 100 characters")
    private String cadastralReference;

    @Size(max = 1000, message = "Legal description cannot exceed 1000 characters")
    private String legalDescription;

    @Min(value = 1, message = "Total rooms must be at least 1")
    @Max(value = 100, message = "Total rooms cannot exceed 100")
    private Integer totalRooms;

    @Min(value = 0, message = "Total bedrooms cannot be negative")
    @Max(value = 50, message = "Total bedrooms cannot exceed 50")
    private Integer totalBedrooms;

    @Min(value = 0, message = "Total bathrooms cannot be negative")
    @Max(value = 50, message = "Total bathrooms cannot exceed 50")
    private Integer totalBathrooms;

    @NotNull(message = "Parking availability is required")
    private Boolean hasParking;

    @Min(value = 0, message = "Parking spaces cannot be negative")
    @Max(value = 100, message = "Parking spaces cannot exceed 100")
    private Integer parkingSpaces;

    @NotNull(message = "Storage availability is required")
    private Boolean hasStorage;

    @DecimalMin(value = "0.00", message = "Storage area cannot be negative")
    @DecimalMax(value = "9999.99", message = "Storage area cannot exceed 9,999.99")
    private BigDecimal storageArea;

    @NotNull(message = "Elevator availability is required")
    private Boolean hasElevator;

    @Min(value = -10, message = "Floor number cannot be below -10")
    @Max(value = 200, message = "Floor number cannot exceed 200")
    private Integer floorNumber;

    private EnergyRatingEnum energyRating;

    @Size(max = 2000, message = "Restrictions cannot exceed 2000 characters")
    private String restrictions;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
