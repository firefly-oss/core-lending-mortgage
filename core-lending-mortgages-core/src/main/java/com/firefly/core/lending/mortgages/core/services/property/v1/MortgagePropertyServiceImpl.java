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


package com.firefly.core.lending.mortgages.core.services.property.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.property.v1.MortgageProperty;
import com.firefly.core.lending.mortgages.models.repositories.property.v1.MortgagePropertyRepository;
import com.firefly.core.lending.mortgages.core.mappers.property.v1.MortgagePropertyMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.property.v1.MortgagePropertyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
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
    public Mono<MortgagePropertyDTO> getById(UUID propertyId) {
        return repository.findById(propertyId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Mortgage property not found with ID: " + propertyId)));
    }

    @Override
    public Mono<MortgagePropertyDTO> update(UUID propertyId, MortgagePropertyDTO dto) {
        return repository.findById(propertyId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Mortgage property not found with ID: " + propertyId)))
                .flatMap(existingProperty -> {
                    MortgageProperty updatedProperty = mapper.toEntity(dto);
                    updatedProperty.setPropertyId(existingProperty.getPropertyId());
                    return repository.save(updatedProperty);
                }).map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID propertyId) {
        return repository.findById(propertyId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Mortgage property not found with ID: " + propertyId)))
                .flatMap(repository::delete);
    }
}
