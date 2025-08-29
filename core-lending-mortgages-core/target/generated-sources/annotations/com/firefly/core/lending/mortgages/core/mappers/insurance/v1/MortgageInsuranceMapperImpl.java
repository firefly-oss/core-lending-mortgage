package com.firefly.core.lending.mortgages.core.mappers.insurance.v1;

import com.firefly.core.lending.mortgages.interfaces.dtos.insurance.v1.MortgageInsuranceDTO;
import com.firefly.core.lending.mortgages.models.entities.insurance.v1.MortgageInsurance;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:21:46+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class MortgageInsuranceMapperImpl implements MortgageInsuranceMapper {

    @Override
    public MortgageInsuranceDTO toDTO(MortgageInsurance entity) {
        if ( entity == null ) {
            return null;
        }

        MortgageInsuranceDTO.MortgageInsuranceDTOBuilder mortgageInsuranceDTO = MortgageInsuranceDTO.builder();

        mortgageInsuranceDTO.insuranceId( entity.getInsuranceId() );
        mortgageInsuranceDTO.mortgageContractId( entity.getMortgageContractId() );
        mortgageInsuranceDTO.insuranceType( entity.getInsuranceType() );
        mortgageInsuranceDTO.policyNumber( entity.getPolicyNumber() );
        mortgageInsuranceDTO.providerName( entity.getProviderName() );
        mortgageInsuranceDTO.providerCode( entity.getProviderCode() );
        mortgageInsuranceDTO.coverageAmount( entity.getCoverageAmount() );
        mortgageInsuranceDTO.deductibleAmount( entity.getDeductibleAmount() );
        mortgageInsuranceDTO.startDate( entity.getStartDate() );
        mortgageInsuranceDTO.endDate( entity.getEndDate() );
        mortgageInsuranceDTO.annualPremium( entity.getAnnualPremium() );
        mortgageInsuranceDTO.premiumFrequency( entity.getPremiumFrequency() );
        mortgageInsuranceDTO.premiumAmount( entity.getPremiumAmount() );
        mortgageInsuranceDTO.bankBeneficiary( entity.getBankBeneficiary() );
        mortgageInsuranceDTO.isActive( entity.getIsActive() );
        mortgageInsuranceDTO.lastPaymentDate( entity.getLastPaymentDate() );
        mortgageInsuranceDTO.nextPaymentDate( entity.getNextPaymentDate() );
        mortgageInsuranceDTO.coverageDetails( entity.getCoverageDetails() );
        mortgageInsuranceDTO.createdAt( entity.getCreatedAt() );
        mortgageInsuranceDTO.updatedAt( entity.getUpdatedAt() );

        return mortgageInsuranceDTO.build();
    }

    @Override
    public MortgageInsurance toEntity(MortgageInsuranceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MortgageInsurance.MortgageInsuranceBuilder mortgageInsurance = MortgageInsurance.builder();

        mortgageInsurance.insuranceId( dto.getInsuranceId() );
        mortgageInsurance.mortgageContractId( dto.getMortgageContractId() );
        mortgageInsurance.insuranceType( dto.getInsuranceType() );
        mortgageInsurance.policyNumber( dto.getPolicyNumber() );
        mortgageInsurance.providerName( dto.getProviderName() );
        mortgageInsurance.providerCode( dto.getProviderCode() );
        mortgageInsurance.coverageAmount( dto.getCoverageAmount() );
        mortgageInsurance.deductibleAmount( dto.getDeductibleAmount() );
        mortgageInsurance.startDate( dto.getStartDate() );
        mortgageInsurance.endDate( dto.getEndDate() );
        mortgageInsurance.annualPremium( dto.getAnnualPremium() );
        mortgageInsurance.premiumFrequency( dto.getPremiumFrequency() );
        mortgageInsurance.premiumAmount( dto.getPremiumAmount() );
        mortgageInsurance.bankBeneficiary( dto.getBankBeneficiary() );
        mortgageInsurance.isActive( dto.getIsActive() );
        mortgageInsurance.lastPaymentDate( dto.getLastPaymentDate() );
        mortgageInsurance.nextPaymentDate( dto.getNextPaymentDate() );
        mortgageInsurance.coverageDetails( dto.getCoverageDetails() );
        mortgageInsurance.createdAt( dto.getCreatedAt() );
        mortgageInsurance.updatedAt( dto.getUpdatedAt() );

        return mortgageInsurance.build();
    }
}
