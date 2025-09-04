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


package com.firefly.core.lending.mortgages.core.services.appraisal.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.appraisal.v1.MortgageAppraisal;
import com.firefly.core.lending.mortgages.models.repositories.appraisal.v1.MortgageAppraisalRepository;
import com.firefly.core.lending.mortgages.core.mappers.appraisal.v1.MortgageAppraisalMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.appraisal.v1.MortgageAppraisalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgageAppraisalServiceImpl implements MortgageAppraisalService {

    @Autowired
    private MortgageAppraisalRepository repository;

    @Autowired
    private MortgageAppraisalMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageAppraisalDTO>> findAll(UUID mortgageApplicationId, FilterRequest<MortgageAppraisalDTO> filterRequest) {
        filterRequest.getFilters().setMortgageApplicationId(mortgageApplicationId);
        return FilterUtils.createFilter(
                MortgageAppraisal.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageAppraisalDTO> create(UUID mortgageApplicationId, MortgageAppraisalDTO dto) {
        dto.setMortgageApplicationId(mortgageApplicationId);
        MortgageAppraisal entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageAppraisalDTO> getById(UUID mortgageApplicationId, UUID appraisalId) {
        return repository.findById(appraisalId)
                .filter(appraisal -> appraisal.getMortgageApplicationId().equals(mortgageApplicationId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageAppraisalDTO> update(UUID mortgageApplicationId, UUID appraisalId, MortgageAppraisalDTO dto) {
        return repository.findById(appraisalId)
                .filter(appraisal -> appraisal.getMortgageApplicationId().equals(mortgageApplicationId))
                .flatMap(existing -> {
                    MortgageAppraisal updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setAppraisalId(appraisalId);
                    updatedEntity.setMortgageApplicationId(mortgageApplicationId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID mortgageApplicationId, UUID appraisalId) {
        return repository.findById(appraisalId)
                .filter(appraisal -> appraisal.getMortgageApplicationId().equals(mortgageApplicationId))
                .flatMap(repository::delete);
    }
}