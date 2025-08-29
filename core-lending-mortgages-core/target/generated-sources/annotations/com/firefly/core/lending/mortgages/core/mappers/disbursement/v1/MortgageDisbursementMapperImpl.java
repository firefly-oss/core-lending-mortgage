package com.firefly.core.lending.mortgages.core.mappers.disbursement.v1;

import com.firefly.core.lending.mortgages.interfaces.dtos.disbursement.v1.MortgageDisbursementDTO;
import com.firefly.core.lending.mortgages.models.entities.disbursement.v1.MortgageDisbursement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:45:07+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class MortgageDisbursementMapperImpl implements MortgageDisbursementMapper {

    @Override
    public MortgageDisbursementDTO toDTO(MortgageDisbursement entity) {
        if ( entity == null ) {
            return null;
        }

        MortgageDisbursementDTO.MortgageDisbursementDTOBuilder mortgageDisbursementDTO = MortgageDisbursementDTO.builder();

        mortgageDisbursementDTO.mortgageDisbursementId( entity.getMortgageDisbursementId() );
        mortgageDisbursementDTO.mortgageContractId( entity.getMortgageContractId() );
        mortgageDisbursementDTO.disbursementAmount( entity.getDisbursementAmount() );
        mortgageDisbursementDTO.disbursementDate( entity.getDisbursementDate() );
        mortgageDisbursementDTO.createdAt( entity.getCreatedAt() );
        mortgageDisbursementDTO.updatedAt( entity.getUpdatedAt() );

        return mortgageDisbursementDTO.build();
    }

    @Override
    public MortgageDisbursement toEntity(MortgageDisbursementDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MortgageDisbursement.MortgageDisbursementBuilder mortgageDisbursement = MortgageDisbursement.builder();

        mortgageDisbursement.mortgageDisbursementId( dto.getMortgageDisbursementId() );
        mortgageDisbursement.mortgageContractId( dto.getMortgageContractId() );
        mortgageDisbursement.disbursementAmount( dto.getDisbursementAmount() );
        mortgageDisbursement.disbursementDate( dto.getDisbursementDate() );
        mortgageDisbursement.createdAt( dto.getCreatedAt() );
        mortgageDisbursement.updatedAt( dto.getUpdatedAt() );

        return mortgageDisbursement.build();
    }
}
