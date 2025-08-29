package com.firefly.core.lending.mortgages.core.mappers.application.v1;

import com.firefly.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationStatusHistoryDTO;
import com.firefly.core.lending.mortgages.models.entities.application.v1.MortgageApplicationStatusHistory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:45:07+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class MortgageApplicationStatusHistoryMapperImpl implements MortgageApplicationStatusHistoryMapper {

    @Override
    public MortgageApplicationStatusHistoryDTO toDTO(MortgageApplicationStatusHistory entity) {
        if ( entity == null ) {
            return null;
        }

        MortgageApplicationStatusHistoryDTO.MortgageApplicationStatusHistoryDTOBuilder mortgageApplicationStatusHistoryDTO = MortgageApplicationStatusHistoryDTO.builder();

        mortgageApplicationStatusHistoryDTO.statusHistoryId( entity.getStatusHistoryId() );
        mortgageApplicationStatusHistoryDTO.mortgageApplicationId( entity.getMortgageApplicationId() );
        mortgageApplicationStatusHistoryDTO.oldStatus( entity.getOldStatus() );
        mortgageApplicationStatusHistoryDTO.newStatus( entity.getNewStatus() );
        mortgageApplicationStatusHistoryDTO.changedBy( entity.getChangedBy() );
        mortgageApplicationStatusHistoryDTO.changeReason( entity.getChangeReason() );
        mortgageApplicationStatusHistoryDTO.comments( entity.getComments() );
        mortgageApplicationStatusHistoryDTO.changedAt( entity.getChangedAt() );
        mortgageApplicationStatusHistoryDTO.createdAt( entity.getCreatedAt() );
        mortgageApplicationStatusHistoryDTO.updatedAt( entity.getUpdatedAt() );

        return mortgageApplicationStatusHistoryDTO.build();
    }

    @Override
    public MortgageApplicationStatusHistory toEntity(MortgageApplicationStatusHistoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MortgageApplicationStatusHistory.MortgageApplicationStatusHistoryBuilder mortgageApplicationStatusHistory = MortgageApplicationStatusHistory.builder();

        mortgageApplicationStatusHistory.statusHistoryId( dto.getStatusHistoryId() );
        mortgageApplicationStatusHistory.mortgageApplicationId( dto.getMortgageApplicationId() );
        mortgageApplicationStatusHistory.oldStatus( dto.getOldStatus() );
        mortgageApplicationStatusHistory.newStatus( dto.getNewStatus() );
        mortgageApplicationStatusHistory.changedBy( dto.getChangedBy() );
        mortgageApplicationStatusHistory.changeReason( dto.getChangeReason() );
        mortgageApplicationStatusHistory.comments( dto.getComments() );
        mortgageApplicationStatusHistory.changedAt( dto.getChangedAt() );
        mortgageApplicationStatusHistory.createdAt( dto.getCreatedAt() );
        mortgageApplicationStatusHistory.updatedAt( dto.getUpdatedAt() );

        return mortgageApplicationStatusHistory.build();
    }
}
