package com.catalis.core.lending.mortgages.core.mappers.document.v1;

import com.catalis.core.lending.mortgages.models.entities.document.v1.MortgageDocument;
import com.catalis.core.lending.mortgages.interfaces.dtos.document.v1.MortgageDocumentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageDocumentMapper {
    MortgageDocumentDTO toDTO(MortgageDocument entity);
    MortgageDocument toEntity(MortgageDocumentDTO dto);
}
