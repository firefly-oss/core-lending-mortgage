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


package com.firefly.core.lending.mortgages.core.services.contract.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.contract.v1.MortgageContract;
import com.firefly.core.lending.mortgages.models.repositories.contract.v1.MortgageContractRepository;
import com.firefly.core.lending.mortgages.core.mappers.contract.v1.MortgageContractMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.contract.v1.MortgageContractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgageContractServiceImpl implements MortgageContractService {

    @Autowired
    private MortgageContractRepository repository;

    @Autowired
    private MortgageContractMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageContractDTO>> findAll(FilterRequest<MortgageContractDTO> filterRequest) {
        return FilterUtils.createFilter(
                MortgageContract.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageContractDTO> create(MortgageContractDTO dto) {
        MortgageContract entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageContractDTO> getById(UUID mortgageContractId) {
        return repository.findById(mortgageContractId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<MortgageContractDTO> update(UUID mortgageContractId, MortgageContractDTO dto) {
        return repository.findById(mortgageContractId)
                .flatMap(existingContract -> {
                    MortgageContract updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setMortgageContractId(existingContract.getMortgageContractId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID mortgageContractId) {
        return repository.findById(mortgageContractId)
                .flatMap(repository::delete);
    }
}
