/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("mortgage_document")
public class MortgageDocument {

    @Id
    @Column("document_id")
    private UUID documentId;

    @Column("mortgage_application_id")
    private UUID mortgageApplicationId;

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