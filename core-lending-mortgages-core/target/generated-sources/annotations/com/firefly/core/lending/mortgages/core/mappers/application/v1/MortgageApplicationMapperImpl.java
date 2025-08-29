package com.firefly.core.lending.mortgages.core.mappers.application.v1;

import com.firefly.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationDTO;
import com.firefly.core.lending.mortgages.models.entities.application.v1.MortgageApplication;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:42:06+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class MortgageApplicationMapperImpl implements MortgageApplicationMapper {

    @Override
    public MortgageApplicationDTO toDTO(MortgageApplication entity) {
        if ( entity == null ) {
            return null;
        }

        MortgageApplicationDTO.MortgageApplicationDTOBuilder mortgageApplicationDTO = MortgageApplicationDTO.builder();

        mortgageApplicationDTO.mortgageApplicationId( entity.getMortgageApplicationId() );
        mortgageApplicationDTO.applicantId( entity.getApplicantId() );
        mortgageApplicationDTO.coApplicantId( entity.getCoApplicantId() );
        mortgageApplicationDTO.propertyId( entity.getPropertyId() );
        mortgageApplicationDTO.productId( entity.getProductId() );
        mortgageApplicationDTO.applicationStatus( entity.getApplicationStatus() );
        mortgageApplicationDTO.applicationChannel( entity.getApplicationChannel() );
        mortgageApplicationDTO.requestedAmount( entity.getRequestedAmount() );
        mortgageApplicationDTO.requestedTermMonths( entity.getRequestedTermMonths() );
        mortgageApplicationDTO.downPayment( entity.getDownPayment() );
        mortgageApplicationDTO.monthlyIncome( entity.getMonthlyIncome() );
        mortgageApplicationDTO.monthlyExpenses( entity.getMonthlyExpenses() );
        mortgageApplicationDTO.employmentType( entity.getEmploymentType() );
        mortgageApplicationDTO.residenceType( entity.getResidenceType() );
        mortgageApplicationDTO.yearsAtCurrentJob( entity.getYearsAtCurrentJob() );
        mortgageApplicationDTO.yearsAtCurrentAddress( entity.getYearsAtCurrentAddress() );
        mortgageApplicationDTO.purpose( entity.getPurpose() );
        mortgageApplicationDTO.existingCustomer( entity.getExistingCustomer() );
        mortgageApplicationDTO.remarks( entity.getRemarks() );
        mortgageApplicationDTO.assignedTo( entity.getAssignedTo() );
        mortgageApplicationDTO.submissionDate( entity.getSubmissionDate() );
        mortgageApplicationDTO.createdAt( entity.getCreatedAt() );
        mortgageApplicationDTO.updatedAt( entity.getUpdatedAt() );

        return mortgageApplicationDTO.build();
    }

    @Override
    public MortgageApplication toEntity(MortgageApplicationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MortgageApplication.MortgageApplicationBuilder mortgageApplication = MortgageApplication.builder();

        mortgageApplication.mortgageApplicationId( dto.getMortgageApplicationId() );
        mortgageApplication.applicantId( dto.getApplicantId() );
        mortgageApplication.coApplicantId( dto.getCoApplicantId() );
        mortgageApplication.propertyId( dto.getPropertyId() );
        mortgageApplication.productId( dto.getProductId() );
        mortgageApplication.applicationStatus( dto.getApplicationStatus() );
        mortgageApplication.applicationChannel( dto.getApplicationChannel() );
        mortgageApplication.requestedAmount( dto.getRequestedAmount() );
        mortgageApplication.requestedTermMonths( dto.getRequestedTermMonths() );
        mortgageApplication.downPayment( dto.getDownPayment() );
        mortgageApplication.monthlyIncome( dto.getMonthlyIncome() );
        mortgageApplication.monthlyExpenses( dto.getMonthlyExpenses() );
        mortgageApplication.employmentType( dto.getEmploymentType() );
        mortgageApplication.residenceType( dto.getResidenceType() );
        mortgageApplication.yearsAtCurrentJob( dto.getYearsAtCurrentJob() );
        mortgageApplication.yearsAtCurrentAddress( dto.getYearsAtCurrentAddress() );
        mortgageApplication.purpose( dto.getPurpose() );
        mortgageApplication.existingCustomer( dto.getExistingCustomer() );
        mortgageApplication.remarks( dto.getRemarks() );
        mortgageApplication.assignedTo( dto.getAssignedTo() );
        mortgageApplication.submissionDate( dto.getSubmissionDate() );
        mortgageApplication.createdAt( dto.getCreatedAt() );
        mortgageApplication.updatedAt( dto.getUpdatedAt() );

        return mortgageApplication.build();
    }
}
