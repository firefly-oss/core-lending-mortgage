package com.firefly.core.lending.mortgages.core.mappers.record.v1;

import com.firefly.core.lending.mortgages.interfaces.dtos.record.v1.MortgagePaymentRecordDTO;
import com.firefly.core.lending.mortgages.models.entities.record.v1.MortgagePaymentRecord;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:45:07+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class MortgagePaymentRecordMapperImpl implements MortgagePaymentRecordMapper {

    @Override
    public MortgagePaymentRecordDTO toDTO(MortgagePaymentRecord entity) {
        if ( entity == null ) {
            return null;
        }

        MortgagePaymentRecordDTO.MortgagePaymentRecordDTOBuilder mortgagePaymentRecordDTO = MortgagePaymentRecordDTO.builder();

        mortgagePaymentRecordDTO.paymentRecordId( entity.getPaymentRecordId() );
        mortgagePaymentRecordDTO.mortgageContractId( entity.getMortgageContractId() );
        mortgagePaymentRecordDTO.paymentScheduleId( entity.getPaymentScheduleId() );
        mortgagePaymentRecordDTO.transactionId( entity.getTransactionId() );
        mortgagePaymentRecordDTO.paymentAmount( entity.getPaymentAmount() );
        mortgagePaymentRecordDTO.paymentDate( entity.getPaymentDate() );
        mortgagePaymentRecordDTO.isPartialPayment( entity.getIsPartialPayment() );
        mortgagePaymentRecordDTO.note( entity.getNote() );
        mortgagePaymentRecordDTO.createdAt( entity.getCreatedAt() );
        mortgagePaymentRecordDTO.updatedAt( entity.getUpdatedAt() );

        return mortgagePaymentRecordDTO.build();
    }

    @Override
    public MortgagePaymentRecord toEntity(MortgagePaymentRecordDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MortgagePaymentRecord.MortgagePaymentRecordBuilder mortgagePaymentRecord = MortgagePaymentRecord.builder();

        mortgagePaymentRecord.paymentRecordId( dto.getPaymentRecordId() );
        mortgagePaymentRecord.mortgageContractId( dto.getMortgageContractId() );
        mortgagePaymentRecord.paymentScheduleId( dto.getPaymentScheduleId() );
        mortgagePaymentRecord.transactionId( dto.getTransactionId() );
        mortgagePaymentRecord.paymentAmount( dto.getPaymentAmount() );
        mortgagePaymentRecord.paymentDate( dto.getPaymentDate() );
        mortgagePaymentRecord.isPartialPayment( dto.getIsPartialPayment() );
        mortgagePaymentRecord.note( dto.getNote() );
        mortgagePaymentRecord.createdAt( dto.getCreatedAt() );
        mortgagePaymentRecord.updatedAt( dto.getUpdatedAt() );

        return mortgagePaymentRecord.build();
    }
}
