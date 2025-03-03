package com.catalis.core.lending.mortgages.core.mappers.record.v1;

import com.catalis.core.lending.mortgages.models.entities.record.v1.MortgagePaymentRecord;
import com.catalis.core.lending.mortgages.interfaces.dtos.record.v1.MortgagePaymentRecordDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgagePaymentRecordMapper {
    MortgagePaymentRecordDTO toDTO(MortgagePaymentRecord entity);
    MortgagePaymentRecord toEntity(MortgagePaymentRecordDTO dto);
}
