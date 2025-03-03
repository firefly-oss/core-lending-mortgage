package com.catalis.core.lending.mortgages.core.mappers.notification.v1;

import com.catalis.core.lending.mortgages.models.entities.notification.v1.MortgageNotification;
import com.catalis.core.lending.mortgages.interfaces.dtos.notification.v1.MortgageNotificationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageNotificationMapper {
    MortgageNotificationDTO toDTO(MortgageNotification entity);
    MortgageNotification toEntity(MortgageNotificationDTO dto);
}
