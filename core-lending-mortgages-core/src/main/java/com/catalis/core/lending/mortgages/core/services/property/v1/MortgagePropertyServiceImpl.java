package com.catalis.core.lending.mortgages.core.services.property.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.models.entities.property.v1.MortgageProperty;
import com.catalis.core.lending.mortgages.models.repositories.property.v1.MortgagePropertyRepository;
import com.catalis.core.lending.mortgages.core.mappers.property.v1.MortgagePropertyMapper;
import com.catalis.core.lending.mortgages.interfaces.dtos.property.v1.MortgagePropertyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class MortgagePropertyServiceImpl implements MortgagePropertyService {

    @Autowired
    private MortgagePropertyRepository repository;

    @Autowired
    private MortgagePropertyMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgagePropertyDTO>> findAll(FilterRequest<MortgagePropertyDTO> filterRequest) {
        return FilterUtils.createFilter(
                MortgageProperty.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgagePropertyDTO> create(MortgagePropertyDTO dto) {
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgagePropertyDTO> getById(Long propertyId) {
        return repository.findById(propertyId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Mortgage property not found with ID: " + propertyId)));
    }

    @Override
    public Mono<MortgagePropertyDTO> update(Long propertyId, MortgagePropertyDTO dto) {
        return repository.findById(propertyId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Mortgage property not found with ID: " + propertyId)))
                .flatMap(existingProperty -> {
                    MortgageProperty updatedProperty = mapper.toEntity(dto);
                    updatedProperty.setPropertyId(existingProperty.getPropertyId());
                    return repository.save(updatedProperty);
                }).map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long propertyId) {
        return repository.findById(propertyId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Mortgage property not found with ID: " + propertyId)))
                .flatMap(repository::delete);
    }
}
