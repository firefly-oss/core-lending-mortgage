package com.firefly.core.lending.mortgages.core.services.application.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.application.v1.MortgageApplicationStatusHistory;
import com.firefly.core.lending.mortgages.models.repositories.application.v1.MortgageApplicationStatusHistoryRepository;
import com.firefly.core.lending.mortgages.core.mappers.application.v1.MortgageApplicationStatusHistoryMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationStatusHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgageApplicationStatusHistoryServiceImpl implements MortgageApplicationStatusHistoryService {

    @Autowired
    private MortgageApplicationStatusHistoryRepository repository;

    @Autowired
    private MortgageApplicationStatusHistoryMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageApplicationStatusHistoryDTO>> findAll(UUID mortgageApplicationId, FilterRequest<MortgageApplicationStatusHistoryDTO> filterRequest) {
        filterRequest.getFilters().setMortgageApplicationId(mortgageApplicationId);
        return FilterUtils.createFilter(
                MortgageApplicationStatusHistory.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageApplicationStatusHistoryDTO> create(UUID mortgageApplicationId, MortgageApplicationStatusHistoryDTO dto) {
        MortgageApplicationStatusHistory entity = mapper.toEntity(dto);
        entity.setMortgageApplicationId(mortgageApplicationId);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageApplicationStatusHistoryDTO> getById(UUID mortgageApplicationId, UUID statusHistoryId) {
        return repository.findById(statusHistoryId)
                .filter(entity -> entity.getMortgageApplicationId().equals(mortgageApplicationId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageApplicationStatusHistoryDTO> update(UUID mortgageApplicationId, UUID statusHistoryId, MortgageApplicationStatusHistoryDTO dto) {
        return repository.findById(statusHistoryId)
                .filter(entity -> entity.getMortgageApplicationId().equals(mortgageApplicationId))
                .flatMap(existingEntity -> {
                    MortgageApplicationStatusHistory updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setStatusHistoryId(existingEntity.getStatusHistoryId());
                    updatedEntity.setMortgageApplicationId(existingEntity.getMortgageApplicationId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID mortgageApplicationId, UUID statusHistoryId) {
        return repository.findById(statusHistoryId)
                .filter(entity -> entity.getMortgageApplicationId().equals(mortgageApplicationId))
                .flatMap(repository::delete);
    }
}
