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


package com.firefly.core.lending.mortgages.core.services.application.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.application.v1.MortgageApplication;
import com.firefly.core.lending.mortgages.models.repositories.application.v1.MortgageApplicationRepository;
import com.firefly.core.lending.mortgages.core.mappers.application.v1.MortgageApplicationMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgageApplicationServiceImpl implements MortgageApplicationService {

    @Autowired
    private MortgageApplicationRepository repository;

    @Autowired
    private MortgageApplicationMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageApplicationDTO>> findAll(FilterRequest<MortgageApplicationDTO> filterRequest) {
        return FilterUtils.createFilter(
                MortgageApplication.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageApplicationDTO> create(MortgageApplicationDTO dto) {
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageApplicationDTO> getById(UUID mortgageApplicationId) {
        return repository.findById(mortgageApplicationId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageApplicationDTO> update(UUID mortgageApplicationId, MortgageApplicationDTO dto) {
        return repository.findById(mortgageApplicationId)
                .flatMap(existing -> {
                    MortgageApplication updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setMortgageApplicationId(mortgageApplicationId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID mortgageApplicationId) {
        return repository.findById(mortgageApplicationId)
                .flatMap(repository::delete);
    }
}
