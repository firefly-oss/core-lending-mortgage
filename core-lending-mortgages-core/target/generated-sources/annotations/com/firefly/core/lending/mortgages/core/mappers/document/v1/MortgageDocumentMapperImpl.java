package com.firefly.core.lending.mortgages.core.mappers.document.v1;

import com.firefly.core.lending.mortgages.interfaces.dtos.document.v1.MortgageDocumentDTO;
import com.firefly.core.lending.mortgages.models.entities.document.v1.MortgageDocument;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:21:46+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class MortgageDocumentMapperImpl implements MortgageDocumentMapper {

    @Override
    public MortgageDocumentDTO toDTO(MortgageDocument entity) {
        if ( entity == null ) {
            return null;
        }

        MortgageDocumentDTO.MortgageDocumentDTOBuilder mortgageDocumentDTO = MortgageDocumentDTO.builder();

        mortgageDocumentDTO.documentId( entity.getDocumentId() );
        mortgageDocumentDTO.mortgageApplicationId( entity.getMortgageApplicationId() );
        mortgageDocumentDTO.documentType( entity.getDocumentType() );
        mortgageDocumentDTO.documentReference( entity.getDocumentReference() );
        mortgageDocumentDTO.documentDate( entity.getDocumentDate() );
        mortgageDocumentDTO.isVerified( entity.getIsVerified() );
        mortgageDocumentDTO.verifiedBy( entity.getVerifiedBy() );
        mortgageDocumentDTO.verifiedAt( entity.getVerifiedAt() );
        mortgageDocumentDTO.verificationNotes( entity.getVerificationNotes() );
        mortgageDocumentDTO.createdAt( entity.getCreatedAt() );
        mortgageDocumentDTO.updatedAt( entity.getUpdatedAt() );

        return mortgageDocumentDTO.build();
    }

    @Override
    public MortgageDocument toEntity(MortgageDocumentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MortgageDocument.MortgageDocumentBuilder mortgageDocument = MortgageDocument.builder();

        mortgageDocument.documentId( dto.getDocumentId() );
        mortgageDocument.mortgageApplicationId( dto.getMortgageApplicationId() );
        mortgageDocument.documentType( dto.getDocumentType() );
        mortgageDocument.documentReference( dto.getDocumentReference() );
        mortgageDocument.documentDate( dto.getDocumentDate() );
        mortgageDocument.isVerified( dto.getIsVerified() );
        mortgageDocument.verifiedBy( dto.getVerifiedBy() );
        mortgageDocument.verifiedAt( dto.getVerifiedAt() );
        mortgageDocument.verificationNotes( dto.getVerificationNotes() );
        mortgageDocument.createdAt( dto.getCreatedAt() );
        mortgageDocument.updatedAt( dto.getUpdatedAt() );

        return mortgageDocument.build();
    }
}
