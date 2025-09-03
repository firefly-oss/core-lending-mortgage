package com.firefly.core.lending.mortgages.interfaces.dtos.document.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.document.v1.DocumentTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageDocumentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID documentId;

    @FilterableId
    @NotNull(message = "Mortgage application ID is required")
    private UUID mortgageApplicationId;

    @NotNull(message = "Document type is required")
    private DocumentTypeEnum documentType;

    @NotBlank(message = "Document reference is required")
    @Size(max = 200, message = "Document reference cannot exceed 200 characters")
    private String documentReference;

    @NotNull(message = "Document date is required")
    @PastOrPresent(message = "Document date cannot be in the future")
    private LocalDate documentDate;

    @NotNull(message = "Verification status is required")
    private Boolean isVerified;

    @Size(max = 100, message = "Verified by cannot exceed 100 characters")
    private String verifiedBy;

    private LocalDateTime verifiedAt;

    @Size(max = 1000, message = "Verification notes cannot exceed 1000 characters")
    private String verificationNotes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}