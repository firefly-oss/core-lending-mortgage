package com.catalis.core.lending.mortgages.core.mappers.application.v1;

import com.catalis.core.lending.mortgages.models.entities.application.v1.MortgageApplication;
import com.catalis.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageApplicationMapper {
    MortgageApplicationDTO toDTO(MortgageApplication entity);
    MortgageApplication toEntity(MortgageApplicationDTO dto);
}
