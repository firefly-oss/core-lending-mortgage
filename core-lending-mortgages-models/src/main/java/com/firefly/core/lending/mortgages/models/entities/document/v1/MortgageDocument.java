package com.firefly.core.lending.mortgages.models.entities.document.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.document.v1.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("mortgage_document")
public class MortgageDocument {

    @Id
    @Column("document_id")
    private Long documentId;

    @Column("mortgage_application_id")
    private Long mortgageApplicationId;

    @Column("document_type")
    private DocumentTypeEnum documentType;

    @Column("document_reference")
    private String documentReference;

    @Column("document_date")
    private LocalDate documentDate;

    @Column("is_verified")
    private Boolean isVerified;

    @Column("verified_by")
    private String verifiedBy;

    @Column("verified_at")
    private LocalDateTime verifiedAt;

    @Column("verification_notes")
    private String verificationNotes;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}