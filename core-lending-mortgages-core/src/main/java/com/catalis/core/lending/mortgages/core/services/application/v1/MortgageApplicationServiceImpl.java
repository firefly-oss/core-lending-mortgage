package com.catalis.core.lending.mortgages.core.services.application.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.models.entities.application.v1.MortgageApplication;
import com.catalis.core.lending.mortgages.models.repositories.application.v1.MortgageApplicationRepository;
import com.catalis.core.lending.mortgages.core.mappers.application.v1.MortgageApplicationMapper;
import com.catalis.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class MortgageApplicationServiceImpl implements MortgageApplicationService {

    @Autowired
    private MortgageApplicationRepository repository;

    @Autowired
    private MortgageApplicationMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageApplicationDTO>> findAll(FilterRequest<MortgageApplicationDTO> filterRequest) {
        return FilterUtils.createFilter(
                MortgageApplication.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageApplicationDTO> create(MortgageApplicationDTO dto) {
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageApplicationDTO> getById(Long mortgageApplicationId) {
        return repository.findById(mortgageApplicationId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageApplicationDTO> update(Long mortgageApplicationId, MortgageApplicationDTO dto) {
        return repository.findById(mortgageApplicationId)
                .flatMap(existing -> {
                    MortgageApplication updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setMortgageApplicationId(mortgageApplicationId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long mortgageApplicationId) {
        return repository.findById(mortgageApplicationId)
                .flatMap(repository::delete);
    }
}
