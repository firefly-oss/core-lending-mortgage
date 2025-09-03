package com.firefly.core.lending.mortgages.core.services.document.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.document.v1.MortgageDocument;
import com.firefly.core.lending.mortgages.models.repositories.document.v1.MortgageDocumentRepository;
import com.firefly.core.lending.mortgages.core.mappers.document.v1.MortgageDocumentMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.document.v1.MortgageDocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgageDocumentServiceImpl implements MortgageDocumentService {

    @Autowired
    private MortgageDocumentRepository repository;

    @Autowired
    private MortgageDocumentMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageDocumentDTO>> findAll(UUID mortgageApplicationId, FilterRequest<MortgageDocumentDTO> filterRequest) {
        filterRequest.getFilters().setMortgageApplicationId(mortgageApplicationId);
        return FilterUtils.createFilter(
                MortgageDocument.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageDocumentDTO> create(UUID mortgageApplicationId, MortgageDocumentDTO dto) {
        dto.setMortgageApplicationId(mortgageApplicationId);
        MortgageDocument mortgageDocument = mapper.toEntity(dto);
        return Mono.from(repository.save(mortgageDocument))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageDocumentDTO> getById(UUID mortgageApplicationId, UUID documentId) {
        return Mono.from(repository.findById(documentId))
                .filter(doc -> doc.getMortgageApplicationId().equals(mortgageApplicationId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageDocumentDTO> update(UUID mortgageApplicationId, UUID documentId, MortgageDocumentDTO dto) {
        return Mono.from(repository.findById(documentId))
                .filter(doc -> doc.getMortgageApplicationId().equals(mortgageApplicationId))
                .flatMap(existingDoc -> {
                    MortgageDocument updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setDocumentId(documentId);
                    updatedEntity.setMortgageApplicationId(mortgageApplicationId);
                    return Mono.from(repository.save(updatedEntity));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID mortgageApplicationId, UUID documentId) {
        return Mono.from(repository.findById(documentId))
                .filter(doc -> doc.getMortgageApplicationId().equals(mortgageApplicationId))
                .flatMap(doc -> Mono.from(repository.delete(doc)))
                .then();
    }
}
