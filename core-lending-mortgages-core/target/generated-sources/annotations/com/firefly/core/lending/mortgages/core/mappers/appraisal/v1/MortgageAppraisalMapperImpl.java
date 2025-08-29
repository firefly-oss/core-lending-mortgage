package com.firefly.core.lending.mortgages.core.mappers.appraisal.v1;

import com.firefly.core.lending.mortgages.interfaces.dtos.appraisal.v1.MortgageAppraisalDTO;
import com.firefly.core.lending.mortgages.models.entities.appraisal.v1.MortgageAppraisal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:45:07+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class MortgageAppraisalMapperImpl implements MortgageAppraisalMapper {

    @Override
    public MortgageAppraisalDTO toDTO(MortgageAppraisal entity) {
        if ( entity == null ) {
            return null;
        }

        MortgageAppraisalDTO.MortgageAppraisalDTOBuilder mortgageAppraisalDTO = MortgageAppraisalDTO.builder();

        mortgageAppraisalDTO.appraisalId( entity.getAppraisalId() );
        mortgageAppraisalDTO.propertyId( entity.getPropertyId() );
        mortgageAppraisalDTO.mortgageApplicationId( entity.getMortgageApplicationId() );
        mortgageAppraisalDTO.appraiserName( entity.getAppraiserName() );
        mortgageAppraisalDTO.licenseNumber( entity.getLicenseNumber() );
        mortgageAppraisalDTO.appraisalType( entity.getAppraisalType() );
        mortgageAppraisalDTO.marketValue( entity.getMarketValue() );
        mortgageAppraisalDTO.rentalValue( entity.getRentalValue() );
        mortgageAppraisalDTO.replacementCost( entity.getReplacementCost() );
        mortgageAppraisalDTO.landValue( entity.getLandValue() );
        mortgageAppraisalDTO.buildingValue( entity.getBuildingValue() );
        mortgageAppraisalDTO.propertyCondition( entity.getPropertyCondition() );
        mortgageAppraisalDTO.locationRating( entity.getLocationRating() );
        mortgageAppraisalDTO.comparableProperties( entity.getComparableProperties() );
        mortgageAppraisalDTO.appraisalDate( entity.getAppraisalDate() );
        mortgageAppraisalDTO.expiryDate( entity.getExpiryDate() );
        mortgageAppraisalDTO.requiresRepairs( entity.getRequiresRepairs() );
        mortgageAppraisalDTO.requiredRepairs( entity.getRequiredRepairs() );
        mortgageAppraisalDTO.repairCost( entity.getRepairCost() );
        mortgageAppraisalDTO.assumptions( entity.getAssumptions() );
        mortgageAppraisalDTO.limitations( entity.getLimitations() );
        mortgageAppraisalDTO.methodology( entity.getMethodology() );
        mortgageAppraisalDTO.comments( entity.getComments() );
        mortgageAppraisalDTO.createdAt( entity.getCreatedAt() );
        mortgageAppraisalDTO.updatedAt( entity.getUpdatedAt() );

        return mortgageAppraisalDTO.build();
    }

    @Override
    public MortgageAppraisal toEntity(MortgageAppraisalDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MortgageAppraisal.MortgageAppraisalBuilder mortgageAppraisal = MortgageAppraisal.builder();

        mortgageAppraisal.appraisalId( dto.getAppraisalId() );
        mortgageAppraisal.propertyId( dto.getPropertyId() );
        mortgageAppraisal.mortgageApplicationId( dto.getMortgageApplicationId() );
        mortgageAppraisal.appraiserName( dto.getAppraiserName() );
        mortgageAppraisal.licenseNumber( dto.getLicenseNumber() );
        mortgageAppraisal.appraisalType( dto.getAppraisalType() );
        mortgageAppraisal.marketValue( dto.getMarketValue() );
        mortgageAppraisal.rentalValue( dto.getRentalValue() );
        mortgageAppraisal.replacementCost( dto.getReplacementCost() );
        mortgageAppraisal.landValue( dto.getLandValue() );
        mortgageAppraisal.buildingValue( dto.getBuildingValue() );
        mortgageAppraisal.propertyCondition( dto.getPropertyCondition() );
        mortgageAppraisal.locationRating( dto.getLocationRating() );
        mortgageAppraisal.comparableProperties( dto.getComparableProperties() );
        mortgageAppraisal.appraisalDate( dto.getAppraisalDate() );
        mortgageAppraisal.expiryDate( dto.getExpiryDate() );
        mortgageAppraisal.requiresRepairs( dto.getRequiresRepairs() );
        mortgageAppraisal.requiredRepairs( dto.getRequiredRepairs() );
        mortgageAppraisal.repairCost( dto.getRepairCost() );
        mortgageAppraisal.assumptions( dto.getAssumptions() );
        mortgageAppraisal.limitations( dto.getLimitations() );
        mortgageAppraisal.methodology( dto.getMethodology() );
        mortgageAppraisal.comments( dto.getComments() );
        mortgageAppraisal.createdAt( dto.getCreatedAt() );
        mortgageAppraisal.updatedAt( dto.getUpdatedAt() );

        return mortgageAppraisal.build();
    }
}
