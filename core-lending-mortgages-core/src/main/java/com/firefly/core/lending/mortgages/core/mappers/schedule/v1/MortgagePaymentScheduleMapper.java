package com.firefly.core.lending.mortgages.core.mappers.schedule.v1;

import com.firefly.core.lending.mortgages.models.entities.schedule.v1.MortgagePaymentSchedule;
import com.firefly.core.lending.mortgages.interfaces.dtos.schedule.v1.MortgagePaymentScheduleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgagePaymentScheduleMapper {
    MortgagePaymentScheduleDTO toDTO(MortgagePaymentSchedule entity);
    MortgagePaymentSchedule toEntity(MortgagePaymentScheduleDTO dto);
}
