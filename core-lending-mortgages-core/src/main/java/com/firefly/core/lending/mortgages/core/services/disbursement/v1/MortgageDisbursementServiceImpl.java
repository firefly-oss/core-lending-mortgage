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


package com.firefly.core.lending.mortgages.core.services.disbursement.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.disbursement.v1.MortgageDisbursement;
import com.firefly.core.lending.mortgages.models.repositories.disbursement.v1.MortgageDisbursementRepository;
import com.firefly.core.lending.mortgages.core.mappers.disbursement.v1.MortgageDisbursementMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.disbursement.v1.MortgageDisbursementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgageDisbursementServiceImpl implements MortgageDisbursementService {

    @Autowired
    private MortgageDisbursementRepository repository;

    @Autowired
    private MortgageDisbursementMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageDisbursementDTO>> findAll(UUID mortgageContractId, FilterRequest<MortgageDisbursementDTO> filterRequest) {
        filterRequest.getFilters().setMortgageContractId(mortgageContractId);
        return FilterUtils.createFilter(
                MortgageDisbursement.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageDisbursementDTO> create(UUID mortgageContractId, MortgageDisbursementDTO dto) {
        dto.setMortgageContractId(mortgageContractId);
        MortgageDisbursement entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageDisbursementDTO> getById(UUID mortgageContractId, UUID mortgageDisbursementId) {
        return repository.findById(mortgageDisbursementId)
                .filter(entity -> mortgageContractId.equals(entity.getMortgageContractId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageDisbursementDTO> update(UUID mortgageContractId, UUID mortgageDisbursementId, MortgageDisbursementDTO dto) {
        return repository.findById(mortgageDisbursementId)
                .filter(entity -> mortgageContractId.equals(entity.getMortgageContractId()))
                .flatMap(entity -> {
                    entity.setDisbursementAmount(dto.getDisbursementAmount());
                    entity.setDisbursementDate(dto.getDisbursementDate());
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID mortgageContractId, UUID mortgageDisbursementId) {
        return repository.findById(mortgageDisbursementId)
                .filter(entity -> mortgageContractId.equals(entity.getMortgageContractId()))
                .flatMap(repository::delete);
    }
}