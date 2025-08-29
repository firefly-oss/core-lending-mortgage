package com.firefly.core.lending.mortgages.core.mappers.schedule.v1;

import com.firefly.core.lending.mortgages.interfaces.dtos.schedule.v1.MortgagePaymentScheduleDTO;
import com.firefly.core.lending.mortgages.models.entities.schedule.v1.MortgagePaymentSchedule;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:42:06+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class MortgagePaymentScheduleMapperImpl implements MortgagePaymentScheduleMapper {

    @Override
    public MortgagePaymentScheduleDTO toDTO(MortgagePaymentSchedule entity) {
        if ( entity == null ) {
            return null;
        }

        MortgagePaymentScheduleDTO.MortgagePaymentScheduleDTOBuilder mortgagePaymentScheduleDTO = MortgagePaymentScheduleDTO.builder();

        mortgagePaymentScheduleDTO.scheduleId( entity.getScheduleId() );
        mortgagePaymentScheduleDTO.mortgageContractId( entity.getMortgageContractId() );
        mortgagePaymentScheduleDTO.installmentNumber( entity.getInstallmentNumber() );
        mortgagePaymentScheduleDTO.dueDate( entity.getDueDate() );
        mortgagePaymentScheduleDTO.principalAmount( entity.getPrincipalAmount() );
        mortgagePaymentScheduleDTO.interestAmount( entity.getInterestAmount() );
        mortgagePaymentScheduleDTO.feeAmount( entity.getFeeAmount() );
        mortgagePaymentScheduleDTO.escrowAmount( entity.getEscrowAmount() );
        mortgagePaymentScheduleDTO.totalAmount( entity.getTotalAmount() );
        mortgagePaymentScheduleDTO.paymentStatus( entity.getPaymentStatus() );
        mortgagePaymentScheduleDTO.paidDate( entity.getPaidDate() );
        mortgagePaymentScheduleDTO.outstandingBalance( entity.getOutstandingBalance() );
        mortgagePaymentScheduleDTO.lateFeeAmount( entity.getLateFeeAmount() );
        mortgagePaymentScheduleDTO.daysLate( entity.getDaysLate() );
        mortgagePaymentScheduleDTO.paymentBreakdown( entity.getPaymentBreakdown() );
        mortgagePaymentScheduleDTO.createdAt( entity.getCreatedAt() );
        mortgagePaymentScheduleDTO.updatedAt( entity.getUpdatedAt() );

        return mortgagePaymentScheduleDTO.build();
    }

    @Override
    public MortgagePaymentSchedule toEntity(MortgagePaymentScheduleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MortgagePaymentSchedule.MortgagePaymentScheduleBuilder mortgagePaymentSchedule = MortgagePaymentSchedule.builder();

        mortgagePaymentSchedule.scheduleId( dto.getScheduleId() );
        mortgagePaymentSchedule.mortgageContractId( dto.getMortgageContractId() );
        mortgagePaymentSchedule.installmentNumber( dto.getInstallmentNumber() );
        mortgagePaymentSchedule.dueDate( dto.getDueDate() );
        mortgagePaymentSchedule.principalAmount( dto.getPrincipalAmount() );
        mortgagePaymentSchedule.interestAmount( dto.getInterestAmount() );
        mortgagePaymentSchedule.feeAmount( dto.getFeeAmount() );
        mortgagePaymentSchedule.escrowAmount( dto.getEscrowAmount() );
        mortgagePaymentSchedule.totalAmount( dto.getTotalAmount() );
        mortgagePaymentSchedule.paymentStatus( dto.getPaymentStatus() );
        mortgagePaymentSchedule.paidDate( dto.getPaidDate() );
        mortgagePaymentSchedule.outstandingBalance( dto.getOutstandingBalance() );
        mortgagePaymentSchedule.lateFeeAmount( dto.getLateFeeAmount() );
        mortgagePaymentSchedule.daysLate( dto.getDaysLate() );
        mortgagePaymentSchedule.paymentBreakdown( dto.getPaymentBreakdown() );
        mortgagePaymentSchedule.createdAt( dto.getCreatedAt() );
        mortgagePaymentSchedule.updatedAt( dto.getUpdatedAt() );

        return mortgagePaymentSchedule.build();
    }
}
