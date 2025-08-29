package com.firefly.core.lending.mortgages.core.mappers.property.v1;

import com.firefly.core.lending.mortgages.interfaces.dtos.property.v1.MortgagePropertyDTO;
import com.firefly.core.lending.mortgages.models.entities.property.v1.MortgageProperty;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:21:46+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class MortgagePropertyMapperImpl implements MortgagePropertyMapper {

    @Override
    public MortgagePropertyDTO toDTO(MortgageProperty entity) {
        if ( entity == null ) {
            return null;
        }

        MortgagePropertyDTO.MortgagePropertyDTOBuilder mortgagePropertyDTO = MortgagePropertyDTO.builder();

        mortgagePropertyDTO.propertyId( entity.getPropertyId() );
        mortgagePropertyDTO.propertyType( entity.getPropertyType() );
        mortgagePropertyDTO.propertyStatus( entity.getPropertyStatus() );
        mortgagePropertyDTO.propertyUse( entity.getPropertyUse() );
        mortgagePropertyDTO.addressLine1( entity.getAddressLine1() );
        mortgagePropertyDTO.addressLine2( entity.getAddressLine2() );
        mortgagePropertyDTO.city( entity.getCity() );
        mortgagePropertyDTO.state( entity.getState() );
        mortgagePropertyDTO.postalCode( entity.getPostalCode() );
        mortgagePropertyDTO.countryCode( entity.getCountryCode() );
        mortgagePropertyDTO.landArea( entity.getLandArea() );
        mortgagePropertyDTO.builtArea( entity.getBuiltArea() );
        mortgagePropertyDTO.constructionYear( entity.getConstructionYear() );
        mortgagePropertyDTO.renovationYear( entity.getRenovationYear() );
        mortgagePropertyDTO.titleNumber( entity.getTitleNumber() );
        mortgagePropertyDTO.cadastralReference( entity.getCadastralReference() );
        mortgagePropertyDTO.legalDescription( entity.getLegalDescription() );
        mortgagePropertyDTO.totalRooms( entity.getTotalRooms() );
        mortgagePropertyDTO.totalBedrooms( entity.getTotalBedrooms() );
        mortgagePropertyDTO.totalBathrooms( entity.getTotalBathrooms() );
        mortgagePropertyDTO.hasParking( entity.getHasParking() );
        mortgagePropertyDTO.parkingSpaces( entity.getParkingSpaces() );
        mortgagePropertyDTO.hasStorage( entity.getHasStorage() );
        mortgagePropertyDTO.storageArea( entity.getStorageArea() );
        mortgagePropertyDTO.hasElevator( entity.getHasElevator() );
        mortgagePropertyDTO.floorNumber( entity.getFloorNumber() );
        mortgagePropertyDTO.energyRating( entity.getEnergyRating() );
        mortgagePropertyDTO.restrictions( entity.getRestrictions() );
        mortgagePropertyDTO.createdAt( entity.getCreatedAt() );
        mortgagePropertyDTO.updatedAt( entity.getUpdatedAt() );

        return mortgagePropertyDTO.build();
    }

    @Override
    public MortgageProperty toEntity(MortgagePropertyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MortgageProperty.MortgagePropertyBuilder mortgageProperty = MortgageProperty.builder();

        mortgageProperty.propertyId( dto.getPropertyId() );
        mortgageProperty.propertyType( dto.getPropertyType() );
        mortgageProperty.propertyStatus( dto.getPropertyStatus() );
        mortgageProperty.propertyUse( dto.getPropertyUse() );
        mortgageProperty.addressLine1( dto.getAddressLine1() );
        mortgageProperty.addressLine2( dto.getAddressLine2() );
        mortgageProperty.city( dto.getCity() );
        mortgageProperty.state( dto.getState() );
        mortgageProperty.postalCode( dto.getPostalCode() );
        mortgageProperty.countryCode( dto.getCountryCode() );
        mortgageProperty.landArea( dto.getLandArea() );
        mortgageProperty.builtArea( dto.getBuiltArea() );
        mortgageProperty.constructionYear( dto.getConstructionYear() );
        mortgageProperty.renovationYear( dto.getRenovationYear() );
        mortgageProperty.titleNumber( dto.getTitleNumber() );
        mortgageProperty.cadastralReference( dto.getCadastralReference() );
        mortgageProperty.legalDescription( dto.getLegalDescription() );
        mortgageProperty.totalRooms( dto.getTotalRooms() );
        mortgageProperty.totalBedrooms( dto.getTotalBedrooms() );
        mortgageProperty.totalBathrooms( dto.getTotalBathrooms() );
        mortgageProperty.hasParking( dto.getHasParking() );
        mortgageProperty.parkingSpaces( dto.getParkingSpaces() );
        mortgageProperty.hasStorage( dto.getHasStorage() );
        mortgageProperty.storageArea( dto.getStorageArea() );
        mortgageProperty.hasElevator( dto.getHasElevator() );
        mortgageProperty.floorNumber( dto.getFloorNumber() );
        mortgageProperty.energyRating( dto.getEnergyRating() );
        mortgageProperty.restrictions( dto.getRestrictions() );
        mortgageProperty.createdAt( dto.getCreatedAt() );
        mortgageProperty.updatedAt( dto.getUpdatedAt() );

        return mortgageProperty.build();
    }
}
