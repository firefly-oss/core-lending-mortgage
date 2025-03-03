package com.catalis.core.lending.mortgages.core.mappers.insurance.v1;

import com.catalis.core.lending.mortgages.models.entities.insurance.v1.MortgageInsurance;
import com.catalis.core.lending.mortgages.interfaces.dtos.insurance.v1.MortgageInsuranceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageInsuranceMapper {
    MortgageInsuranceDTO toDTO(MortgageInsurance entity);
    MortgageInsurance toEntity(MortgageInsuranceDTO dto);
}
