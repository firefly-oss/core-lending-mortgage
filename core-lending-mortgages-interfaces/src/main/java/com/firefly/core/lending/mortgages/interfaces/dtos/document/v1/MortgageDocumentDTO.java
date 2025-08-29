package com.firefly.core.lending.mortgages.interfaces.dtos.document.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.document.v1.DocumentTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageDocumentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long documentId;

    @FilterableId
    private Long mortgageApplicationId;

    private DocumentTypeEnum documentType;
    private String documentReference;
    private LocalDate documentDate;
    private Boolean isVerified;
    private String verifiedBy;
    private LocalDateTime verifiedAt;
    private String verificationNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}