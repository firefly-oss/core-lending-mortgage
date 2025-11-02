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


package com.firefly.core.lending.mortgages.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.MortgageAgreementService;
import com.firefly.core.lending.mortgages.models.entities.MortgageAgreement;
import com.firefly.core.lending.mortgages.models.repositories.MortgageAgreementRepository;
import com.firefly.core.lending.mortgages.core.mappers.MortgageAgreementMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.MortgageAgreementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgageAgreementServiceImpl implements MortgageAgreementService {

    @Autowired
    private MortgageAgreementRepository repository;

    @Autowired
    private MortgageAgreementMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageAgreementDTO>> findAll(FilterRequest<MortgageAgreementDTO> filterRequest) {
        return FilterUtils.createFilter(
                MortgageAgreement.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageAgreementDTO> create(MortgageAgreementDTO dto) {
        MortgageAgreement entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageAgreementDTO> getById(UUID mortgageAgreementId) {
        return repository.findById(mortgageAgreementId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<MortgageAgreementDTO> update(UUID mortgageAgreementId, MortgageAgreementDTO dto) {
        return repository.findById(mortgageAgreementId)
                .flatMap(existingAgreement -> {
                    MortgageAgreement updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setMortgageAgreementId(existingAgreement.getMortgageAgreementId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID mortgageAgreementId) {
        return repository.findById(mortgageAgreementId)
                .flatMap(repository::delete);
    }
}
