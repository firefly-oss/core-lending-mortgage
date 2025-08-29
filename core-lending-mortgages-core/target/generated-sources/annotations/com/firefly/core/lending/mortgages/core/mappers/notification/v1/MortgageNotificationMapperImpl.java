package com.firefly.core.lending.mortgages.core.mappers.notification.v1;

import com.firefly.core.lending.mortgages.interfaces.dtos.notification.v1.MortgageNotificationDTO;
import com.firefly.core.lending.mortgages.models.entities.notification.v1.MortgageNotification;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:21:46+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class MortgageNotificationMapperImpl implements MortgageNotificationMapper {

    @Override
    public MortgageNotificationDTO toDTO(MortgageNotification entity) {
        if ( entity == null ) {
            return null;
        }

        MortgageNotificationDTO.MortgageNotificationDTOBuilder mortgageNotificationDTO = MortgageNotificationDTO.builder();

        mortgageNotificationDTO.notificationId( entity.getNotificationId() );
        mortgageNotificationDTO.mortgageContractId( entity.getMortgageContractId() );
        mortgageNotificationDTO.notificationType( entity.getNotificationType() );
        mortgageNotificationDTO.priority( entity.getPriority() );
        mortgageNotificationDTO.recipient( entity.getRecipient() );
        mortgageNotificationDTO.message( entity.getMessage() );
        mortgageNotificationDTO.isSent( entity.getIsSent() );
        mortgageNotificationDTO.sentAt( entity.getSentAt() );
        mortgageNotificationDTO.createdAt( entity.getCreatedAt() );

        return mortgageNotificationDTO.build();
    }

    @Override
    public MortgageNotification toEntity(MortgageNotificationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MortgageNotification.MortgageNotificationBuilder mortgageNotification = MortgageNotification.builder();

        mortgageNotification.notificationId( dto.getNotificationId() );
        mortgageNotification.mortgageContractId( dto.getMortgageContractId() );
        mortgageNotification.notificationType( dto.getNotificationType() );
        mortgageNotification.priority( dto.getPriority() );
        mortgageNotification.recipient( dto.getRecipient() );
        mortgageNotification.message( dto.getMessage() );
        mortgageNotification.isSent( dto.getIsSent() );
        mortgageNotification.sentAt( dto.getSentAt() );
        mortgageNotification.createdAt( dto.getCreatedAt() );

        return mortgageNotification.build();
    }
}
