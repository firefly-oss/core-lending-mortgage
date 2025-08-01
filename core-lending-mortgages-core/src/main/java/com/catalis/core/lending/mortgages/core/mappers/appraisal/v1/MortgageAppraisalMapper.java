package com.catalis.core.lending.mortgages.core.mappers.appraisal.v1;

import com.catalis.core.lending.mortgages.models.entities.appraisal.v1.MortgageAppraisal;
import com.catalis.core.lending.mortgages.interfaces.dtos.appraisal.v1.MortgageAppraisalDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageAppraisalMapper {
    MortgageAppraisalDTO toDTO(MortgageAppraisal entity);
    MortgageAppraisal toEntity(MortgageAppraisalDTO dto);
}
