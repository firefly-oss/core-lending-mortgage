package com.firefly.core.lending.mortgages.core.mappers.contract.v1;

import com.firefly.core.lending.mortgages.interfaces.dtos.contract.v1.MortgageContractDTO;
import com.firefly.core.lending.mortgages.models.entities.contract.v1.MortgageContract;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:45:07+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class MortgageContractMapperImpl implements MortgageContractMapper {

    @Override
    public MortgageContractDTO toDTO(MortgageContract entity) {
        if ( entity == null ) {
            return null;
        }

        MortgageContractDTO.MortgageContractDTOBuilder mortgageContractDTO = MortgageContractDTO.builder();

        mortgageContractDTO.mortgageContractId( entity.getMortgageContractId() );
        mortgageContractDTO.mortgageApplicationId( entity.getMortgageApplicationId() );
        mortgageContractDTO.propertyId( entity.getPropertyId() );
        mortgageContractDTO.contractNumber( entity.getContractNumber() );
        mortgageContractDTO.contractStatus( entity.getContractStatus() );
        mortgageContractDTO.loanAmount( entity.getLoanAmount() );
        mortgageContractDTO.interestRate( entity.getInterestRate() );
        mortgageContractDTO.rateType( entity.getRateType() );
        mortgageContractDTO.referenceRate( entity.getReferenceRate() );
        mortgageContractDTO.marginRate( entity.getMarginRate() );
        mortgageContractDTO.termMonths( entity.getTermMonths() );
        mortgageContractDTO.startDate( entity.getStartDate() );
        mortgageContractDTO.maturityDate( entity.getMaturityDate() );
        mortgageContractDTO.monthlyPayment( entity.getMonthlyPayment() );
        mortgageContractDTO.earlyRepaymentFee( entity.getEarlyRepaymentFee() );
        mortgageContractDTO.assumable( entity.getAssumable() );
        mortgageContractDTO.taxRate( entity.getTaxRate() );
        mortgageContractDTO.specialConditions( entity.getSpecialConditions() );
        mortgageContractDTO.notaryReference( entity.getNotaryReference() );
        mortgageContractDTO.signingDate( entity.getSigningDate() );
        mortgageContractDTO.createdAt( entity.getCreatedAt() );
        mortgageContractDTO.updatedAt( entity.getUpdatedAt() );

        return mortgageContractDTO.build();
    }

    @Override
    public MortgageContract toEntity(MortgageContractDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MortgageContract.MortgageContractBuilder mortgageContract = MortgageContract.builder();

        mortgageContract.mortgageContractId( dto.getMortgageContractId() );
        mortgageContract.mortgageApplicationId( dto.getMortgageApplicationId() );
        mortgageContract.propertyId( dto.getPropertyId() );
        mortgageContract.contractNumber( dto.getContractNumber() );
        mortgageContract.contractStatus( dto.getContractStatus() );
        mortgageContract.loanAmount( dto.getLoanAmount() );
        mortgageContract.interestRate( dto.getInterestRate() );
        mortgageContract.rateType( dto.getRateType() );
        mortgageContract.referenceRate( dto.getReferenceRate() );
        mortgageContract.marginRate( dto.getMarginRate() );
        mortgageContract.termMonths( dto.getTermMonths() );
        mortgageContract.startDate( dto.getStartDate() );
        mortgageContract.maturityDate( dto.getMaturityDate() );
        mortgageContract.monthlyPayment( dto.getMonthlyPayment() );
        mortgageContract.earlyRepaymentFee( dto.getEarlyRepaymentFee() );
        mortgageContract.assumable( dto.getAssumable() );
        mortgageContract.taxRate( dto.getTaxRate() );
        mortgageContract.specialConditions( dto.getSpecialConditions() );
        mortgageContract.notaryReference( dto.getNotaryReference() );
        mortgageContract.signingDate( dto.getSigningDate() );
        mortgageContract.createdAt( dto.getCreatedAt() );
        mortgageContract.updatedAt( dto.getUpdatedAt() );

        return mortgageContract.build();
    }
}
