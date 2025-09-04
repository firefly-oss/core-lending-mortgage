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


package com.firefly.core.lending.mortgages.core.services.schedule.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.schedule.v1.MortgagePaymentSchedule;
import com.firefly.core.lending.mortgages.models.repositories.schedule.v1.MortgagePaymentScheduleRepository;
import com.firefly.core.lending.mortgages.core.mappers.schedule.v1.MortgagePaymentScheduleMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.schedule.v1.MortgagePaymentScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgagePaymentScheduleServiceImpl implements MortgagePaymentScheduleService {

    @Autowired
    private MortgagePaymentScheduleRepository repository;

    @Autowired
    private MortgagePaymentScheduleMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgagePaymentScheduleDTO>> findAll(UUID mortgageContractId, FilterRequest<MortgagePaymentScheduleDTO> filterRequest) {
        filterRequest.getFilters().setMortgageContractId(mortgageContractId);
        return FilterUtils.createFilter(
                MortgagePaymentSchedule.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgagePaymentScheduleDTO> create(UUID mortgageContractId, MortgagePaymentScheduleDTO dto) {
        dto.setMortgageContractId(mortgageContractId);
        MortgagePaymentSchedule entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgagePaymentScheduleDTO> getById(UUID mortgageContractId, UUID scheduleId) {
        return repository.findById(scheduleId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgagePaymentScheduleDTO> update(UUID mortgageContractId, UUID scheduleId, MortgagePaymentScheduleDTO dto) {
        return repository.findById(scheduleId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .flatMap(entity -> {
                    MortgagePaymentSchedule updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setScheduleId(scheduleId);
                    updatedEntity.setMortgageContractId(mortgageContractId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID mortgageContractId, UUID scheduleId) {
        return repository.findById(scheduleId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .flatMap(repository::delete);
    }
}
