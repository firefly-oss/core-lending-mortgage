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


package com.firefly.core.lending.mortgages.core.services.notification.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.notification.v1.MortgageNotification;
import com.firefly.core.lending.mortgages.models.repositories.notification.v1.MortgageNotificationRepository;
import com.firefly.core.lending.mortgages.core.mappers.notification.v1.MortgageNotificationMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.notification.v1.MortgageNotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgageNotificationServiceImpl implements MortgageNotificationService {

    @Autowired
    private MortgageNotificationRepository repository;

    @Autowired
    private MortgageNotificationMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageNotificationDTO>> findAll(UUID mortgageContractId, FilterRequest<MortgageNotificationDTO> filterRequest) {
        filterRequest.getFilters().setMortgageContractId(mortgageContractId);
        return FilterUtils.createFilter(
                MortgageNotification.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageNotificationDTO> create(UUID mortgageContractId, MortgageNotificationDTO dto) {
        dto.setMortgageContractId(mortgageContractId);
        MortgageNotification entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageNotificationDTO> getById(UUID mortgageContractId, UUID notificationId) {
        return repository.findById(notificationId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageNotificationDTO> update(UUID mortgageContractId, UUID notificationId, MortgageNotificationDTO dto) {
        return repository.findById(notificationId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .flatMap(entity -> {
                    dto.setNotificationId(notificationId);
                    dto.setMortgageContractId(mortgageContractId);
                    MortgageNotification updatedEntity = mapper.toEntity(dto);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID mortgageContractId, UUID notificationId) {
        return repository.findById(notificationId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .flatMap(repository::delete);
    }
}
