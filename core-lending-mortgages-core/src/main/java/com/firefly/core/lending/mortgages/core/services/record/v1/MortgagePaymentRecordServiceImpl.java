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


package com.firefly.core.lending.mortgages.core.services.record.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.record.v1.MortgagePaymentRecord;
import com.firefly.core.lending.mortgages.models.repositories.record.v1.MortgagePaymentRecordRepository;
import com.firefly.core.lending.mortgages.core.mappers.record.v1.MortgagePaymentRecordMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.record.v1.MortgagePaymentRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgagePaymentRecordServiceImpl implements MortgagePaymentRecordService {

    @Autowired
    private MortgagePaymentRecordRepository repository;

    @Autowired
    private MortgagePaymentRecordMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgagePaymentRecordDTO>> findAll(UUID mortgageContractId, FilterRequest<MortgagePaymentRecordDTO> filterRequest) {
        filterRequest.getFilters().setMortgageContractId(mortgageContractId);
        return FilterUtils.createFilter(
                MortgagePaymentRecord.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgagePaymentRecordDTO> create(UUID mortgageContractId, MortgagePaymentRecordDTO dto) {
        dto.setMortgageContractId(mortgageContractId);
        MortgagePaymentRecord entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgagePaymentRecordDTO> getById(UUID mortgageContractId, UUID paymentRecordId) {
        return repository.findById(paymentRecordId)
                .filter(record -> record.getMortgageContractId().equals(mortgageContractId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgagePaymentRecordDTO> update(UUID mortgageContractId, UUID paymentRecordId, MortgagePaymentRecordDTO dto) {
        return repository.findById(paymentRecordId)
                .filter(record -> record.getMortgageContractId().equals(mortgageContractId))
                .flatMap(record -> {
                    dto.setPaymentRecordId(paymentRecordId);
                    dto.setMortgageContractId(mortgageContractId);
                    MortgagePaymentRecord updatedEntity = mapper.toEntity(dto);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID mortgageContractId, UUID paymentRecordId) {
        return repository.findById(paymentRecordId)
                .filter(record -> record.getMortgageContractId().equals(mortgageContractId))
                .flatMap(repository::delete);
    }
}