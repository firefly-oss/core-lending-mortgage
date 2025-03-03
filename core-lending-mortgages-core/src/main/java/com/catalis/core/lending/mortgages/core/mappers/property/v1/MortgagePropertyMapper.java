package com.catalis.core.lending.mortgages.core.mappers.property.v1;

import com.catalis.core.lending.mortgages.models.entities.property.v1.MortgageProperty;
import com.catalis.core.lending.mortgages.interfaces.dtos.property.v1.MortgagePropertyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgagePropertyMapper {
    MortgagePropertyDTO toDTO(MortgageProperty entity);
    MortgageProperty toEntity(MortgagePropertyDTO dto);
}