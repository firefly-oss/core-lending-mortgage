package com.catalis.core.lending.mortgages.core.mappers.contract.v1;

import com.catalis.core.lending.mortgages.models.entities.contract.v1.MortgageContract;
import com.catalis.core.lending.mortgages.interfaces.dtos.contract.v1.MortgageContractDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageContractMapper {
    MortgageContractDTO toDTO(MortgageContract entity);
    MortgageContract toEntity(MortgageContractDTO dto);
}