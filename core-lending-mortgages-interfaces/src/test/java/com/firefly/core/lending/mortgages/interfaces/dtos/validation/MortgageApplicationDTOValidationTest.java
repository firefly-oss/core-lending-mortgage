package com.firefly.core.lending.mortgages.interfaces.dtos.validation;

import com.firefly.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationDTO;
import com.firefly.core.lending.mortgages.interfaces.enums.application.v1.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MortgageApplicationDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidMortgageApplicationDTO() {
        MortgageApplicationDTO dto = createValidDTO();
        Set<ConstraintViolation<MortgageApplicationDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid DTO should not have validation errors");
    }

    @Test
    void testNullApplicantId() {
        MortgageApplicationDTO dto = createValidDTO();
        dto.setApplicantId(null);
        
        Set<ConstraintViolation<MortgageApplicationDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageApplicationDTO> violation = violations.iterator().next();
        assertEquals("Applicant ID is required", violation.getMessage());
        assertEquals("applicantId", violation.getPropertyPath().toString());
    }

    @Test
    void testNullPropertyId() {
        MortgageApplicationDTO dto = createValidDTO();
        dto.setPropertyId(null);
        
        Set<ConstraintViolation<MortgageApplicationDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageApplicationDTO> violation = violations.iterator().next();
        assertEquals("Property ID is required", violation.getMessage());
        assertEquals("propertyId", violation.getPropertyPath().toString());
    }

    @Test
    void testNullRequestedAmount() {
        MortgageApplicationDTO dto = createValidDTO();
        dto.setRequestedAmount(null);
        
        Set<ConstraintViolation<MortgageApplicationDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageApplicationDTO> violation = violations.iterator().next();
        assertEquals("Requested amount is required", violation.getMessage());
        assertEquals("requestedAmount", violation.getPropertyPath().toString());
    }

    @Test
    void testInvalidRequestedAmount() {
        MortgageApplicationDTO dto = createValidDTO();
        dto.setRequestedAmount(BigDecimal.ZERO);
        
        Set<ConstraintViolation<MortgageApplicationDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageApplicationDTO> violation = violations.iterator().next();
        assertEquals("Requested amount must be greater than 0", violation.getMessage());
        assertEquals("requestedAmount", violation.getPropertyPath().toString());
    }

    @Test
    void testInvalidTermMonths() {
        MortgageApplicationDTO dto = createValidDTO();
        dto.setRequestedTermMonths(0);
        
        Set<ConstraintViolation<MortgageApplicationDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageApplicationDTO> violation = violations.iterator().next();
        assertEquals("Term must be at least 1 month", violation.getMessage());
        assertEquals("requestedTermMonths", violation.getPropertyPath().toString());
    }

    @Test
    void testExcessiveTermMonths() {
        MortgageApplicationDTO dto = createValidDTO();
        dto.setRequestedTermMonths(500);
        
        Set<ConstraintViolation<MortgageApplicationDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageApplicationDTO> violation = violations.iterator().next();
        assertEquals("Term cannot exceed 480 months (40 years)", violation.getMessage());
        assertEquals("requestedTermMonths", violation.getPropertyPath().toString());
    }

    @Test
    void testNegativeMonthlyIncome() {
        MortgageApplicationDTO dto = createValidDTO();
        dto.setMonthlyIncome(BigDecimal.valueOf(-1000));
        
        Set<ConstraintViolation<MortgageApplicationDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageApplicationDTO> violation = violations.iterator().next();
        assertEquals("Monthly income must be greater than 0", violation.getMessage());
        assertEquals("monthlyIncome", violation.getPropertyPath().toString());
    }

    @Test
    void testLongRemarks() {
        MortgageApplicationDTO dto = createValidDTO();
        dto.setRemarks("A".repeat(1001)); // Exceeds 1000 character limit
        
        Set<ConstraintViolation<MortgageApplicationDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageApplicationDTO> violation = violations.iterator().next();
        assertEquals("Remarks cannot exceed 1000 characters", violation.getMessage());
        assertEquals("remarks", violation.getPropertyPath().toString());
    }

    private MortgageApplicationDTO createValidDTO() {
        return MortgageApplicationDTO.builder()
                .applicantId(UUID.randomUUID())
                .propertyId(UUID.randomUUID())
                .productId(UUID.randomUUID())
                .applicationStatus(ApplicationStatusEnum.DRAFT)
                .applicationChannel(ApplicationChannelEnum.ONLINE)
                .requestedAmount(BigDecimal.valueOf(250000))
                .requestedTermMonths(360)
                .downPayment(BigDecimal.valueOf(50000))
                .monthlyIncome(BigDecimal.valueOf(5000))
                .monthlyExpenses(BigDecimal.valueOf(2000))
                .employmentType(EmploymentTypeEnum.SALARIED)
                .residenceType(ResidenceTypeEnum.OWNED)
                .yearsAtCurrentJob(5)
                .yearsAtCurrentAddress(3)
                .purpose(PurposeEnum.PURCHASE)
                .existingCustomer(false)
                .remarks("Test application")
                .assignedTo("John Doe")
                .submissionDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
