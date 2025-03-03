package com.catalis.core.lending.mortgages.core.mappers.disbursement.v1;

import com.catalis.core.lending.mortgages.models.entities.disbursement.v1.MortgageDisbursement;
import com.catalis.core.lending.mortgages.interfaces.dtos.disbursement.v1.MortgageDisbursementDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageDisbursementMapper {
    MortgageDisbursementDTO toDTO(MortgageDisbursement entity);
    MortgageDisbursement toEntity(MortgageDisbursementDTO dto);
}
