package com.firefly.core.lending.mortgages.interfaces.dtos.validation;

import com.firefly.core.lending.mortgages.interfaces.dtos.contract.v1.MortgageContractDTO;
import com.firefly.core.lending.mortgages.interfaces.enums.contract.v1.ContractStatusEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.contract.v1.RateTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MortgageContractDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidMortgageContractDTO() {
        MortgageContractDTO dto = createValidDTO();
        Set<ConstraintViolation<MortgageContractDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid DTO should not have validation errors");
    }

    @Test
    void testBlankContractNumber() {
        MortgageContractDTO dto = createValidDTO();
        dto.setContractNumber("");
        
        Set<ConstraintViolation<MortgageContractDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageContractDTO> violation = violations.iterator().next();
        assertEquals("Contract number is required", violation.getMessage());
        assertEquals("contractNumber", violation.getPropertyPath().toString());
    }

    @Test
    void testNullLoanAmount() {
        MortgageContractDTO dto = createValidDTO();
        dto.setLoanAmount(null);
        
        Set<ConstraintViolation<MortgageContractDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageContractDTO> violation = violations.iterator().next();
        assertEquals("Loan amount is required", violation.getMessage());
        assertEquals("loanAmount", violation.getPropertyPath().toString());
    }

    @Test
    void testZeroLoanAmount() {
        MortgageContractDTO dto = createValidDTO();
        dto.setLoanAmount(BigDecimal.ZERO);
        
        Set<ConstraintViolation<MortgageContractDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageContractDTO> violation = violations.iterator().next();
        assertEquals("Loan amount must be greater than 0", violation.getMessage());
        assertEquals("loanAmount", violation.getPropertyPath().toString());
    }

    @Test
    void testExcessiveInterestRate() {
        MortgageContractDTO dto = createValidDTO();
        dto.setInterestRate(BigDecimal.valueOf(150.00));
        
        Set<ConstraintViolation<MortgageContractDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageContractDTO> violation = violations.iterator().next();
        assertEquals("Interest rate cannot exceed 100%", violation.getMessage());
        assertEquals("interestRate", violation.getPropertyPath().toString());
    }

    @Test
    void testInvalidTermMonths() {
        MortgageContractDTO dto = createValidDTO();
        dto.setTermMonths(0);
        
        Set<ConstraintViolation<MortgageContractDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageContractDTO> violation = violations.iterator().next();
        assertEquals("Term must be at least 1 month", violation.getMessage());
        assertEquals("termMonths", violation.getPropertyPath().toString());
    }

    @Test
    void testPastMaturityDate() {
        MortgageContractDTO dto = createValidDTO();
        dto.setMaturityDate(LocalDate.now().minusDays(1));
        
        Set<ConstraintViolation<MortgageContractDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageContractDTO> violation = violations.iterator().next();
        assertEquals("Maturity date must be in the future", violation.getMessage());
        assertEquals("maturityDate", violation.getPropertyPath().toString());
    }

    @Test
    void testNegativeMonthlyPayment() {
        MortgageContractDTO dto = createValidDTO();
        dto.setMonthlyPayment(BigDecimal.valueOf(-100));
        
        Set<ConstraintViolation<MortgageContractDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        
        ConstraintViolation<MortgageContractDTO> violation = violations.iterator().next();
        assertEquals("Monthly payment must be greater than 0", violation.getMessage());
        assertEquals("monthlyPayment", violation.getPropertyPath().toString());
    }

    private MortgageContractDTO createValidDTO() {
        return MortgageContractDTO.builder()
                .mortgageApplicationId(UUID.randomUUID())
                .propertyId(UUID.randomUUID())
                .contractNumber("MC-2024-001")
                .contractStatus(ContractStatusEnum.ACTIVE)
                .loanAmount(BigDecimal.valueOf(200000))
                .interestRate(BigDecimal.valueOf(3.5))
                .rateType(RateTypeEnum.FIXED)
                .referenceRate("EURIBOR")
                .marginRate(BigDecimal.valueOf(1.5))
                .termMonths(360)
                .startDate(LocalDate.now())
                .maturityDate(LocalDate.now().plusYears(30))
                .monthlyPayment(BigDecimal.valueOf(1200))
                .earlyRepaymentFee(BigDecimal.valueOf(5000))
                .assumable(false)
                .taxRate(BigDecimal.valueOf(0.5))
                .specialConditions("Standard terms")
                .notaryReference("NOT-2024-001")
                .signingDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
