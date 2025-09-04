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


package com.firefly.core.lending.mortgages.core.services.insurance.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.insurance.v1.MortgageInsurance;
import com.firefly.core.lending.mortgages.models.repositories.insurance.v1.MortgageInsuranceRepository;
import com.firefly.core.lending.mortgages.core.mappers.insurance.v1.MortgageInsuranceMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.insurance.v1.MortgageInsuranceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgageInsuranceServiceImpl implements MortgageInsuranceService {

    @Autowired
    private MortgageInsuranceRepository repository;

    @Autowired
    private MortgageInsuranceMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageInsuranceDTO>> findAll(UUID mortgageContractId, FilterRequest<MortgageInsuranceDTO> filterRequest) {
        filterRequest.getFilters().setMortgageContractId(mortgageContractId);
        return FilterUtils.createFilter(
                MortgageInsurance.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageInsuranceDTO> create(UUID mortgageContractId, MortgageInsuranceDTO dto) {
        dto.setMortgageContractId(mortgageContractId);
        MortgageInsurance entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageInsuranceDTO> getById(UUID mortgageContractId, UUID insuranceId) {
        return repository.findById(insuranceId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageInsuranceDTO> update(UUID mortgageContractId, UUID insuranceId, MortgageInsuranceDTO dto) {
        return repository.findById(insuranceId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .flatMap(existingEntity -> {
                    MortgageInsurance updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setInsuranceId(insuranceId);
                    updatedEntity.setMortgageContractId(mortgageContractId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID mortgageContractId, UUID insuranceId) {
        return repository.findById(insuranceId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .flatMap(repository::delete);
    }
}
