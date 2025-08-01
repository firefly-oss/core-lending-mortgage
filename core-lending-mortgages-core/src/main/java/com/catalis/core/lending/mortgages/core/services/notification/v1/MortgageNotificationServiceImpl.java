package com.catalis.core.lending.mortgages.core.services.notification.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.models.entities.notification.v1.MortgageNotification;
import com.catalis.core.lending.mortgages.models.repositories.notification.v1.MortgageNotificationRepository;
import com.catalis.core.lending.mortgages.core.mappers.notification.v1.MortgageNotificationMapper;
import com.catalis.core.lending.mortgages.interfaces.dtos.notification.v1.MortgageNotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class MortgageNotificationServiceImpl implements MortgageNotificationService {

    @Autowired
    private MortgageNotificationRepository repository;

    @Autowired
    private MortgageNotificationMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageNotificationDTO>> findAll(Long mortgageContractId, FilterRequest<MortgageNotificationDTO> filterRequest) {
        filterRequest.getFilters().setMortgageContractId(mortgageContractId);
        return FilterUtils.createFilter(
                MortgageNotification.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageNotificationDTO> create(Long mortgageContractId, MortgageNotificationDTO dto) {
        dto.setMortgageContractId(mortgageContractId);
        MortgageNotification entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageNotificationDTO> getById(Long mortgageContractId, Long notificationId) {
        return repository.findById(notificationId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageNotificationDTO> update(Long mortgageContractId, Long notificationId, MortgageNotificationDTO dto) {
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
    public Mono<Void> delete(Long mortgageContractId, Long notificationId) {
        return repository.findById(notificationId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .flatMap(repository::delete);
    }
}
