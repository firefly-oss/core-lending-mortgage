package com.catalis.core.lending.mortgages.core.mappers.application.v1;

import com.catalis.core.lending.mortgages.models.entities.application.v1.MortgageApplicationStatusHistory;
import com.catalis.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationStatusHistoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageApplicationStatusHistoryMapper {
    MortgageApplicationStatusHistoryDTO toDTO(MortgageApplicationStatusHistory entity);
    MortgageApplicationStatusHistory toEntity(MortgageApplicationStatusHistoryDTO dto);
}
