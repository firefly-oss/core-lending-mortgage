package com.catalis.core.lending.mortgages.core.services.appraisal.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.models.entities.appraisal.v1.MortgageAppraisal;
import com.catalis.core.lending.mortgages.models.repositories.appraisal.v1.MortgageAppraisalRepository;
import com.catalis.core.lending.mortgages.core.mappers.appraisal.v1.MortgageAppraisalMapper;
import com.catalis.core.lending.mortgages.interfaces.dtos.appraisal.v1.MortgageAppraisalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class MortgageAppraisalServiceImpl implements MortgageAppraisalService {

    @Autowired
    private MortgageAppraisalRepository repository;

    @Autowired
    private MortgageAppraisalMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageAppraisalDTO>> findAll(Long mortgageApplicationId, FilterRequest<MortgageAppraisalDTO> filterRequest) {
        filterRequest.getFilters().setMortgageApplicationId(mortgageApplicationId);
        return FilterUtils.createFilter(
                MortgageAppraisal.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageAppraisalDTO> create(Long mortgageApplicationId, MortgageAppraisalDTO dto) {
        dto.setMortgageApplicationId(mortgageApplicationId);
        MortgageAppraisal entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageAppraisalDTO> getById(Long mortgageApplicationId, Long appraisalId) {
        return repository.findById(appraisalId)
                .filter(appraisal -> appraisal.getMortgageApplicationId().equals(mortgageApplicationId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageAppraisalDTO> update(Long mortgageApplicationId, Long appraisalId, MortgageAppraisalDTO dto) {
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
    public Mono<Void> delete(Long mortgageApplicationId, Long appraisalId) {
        return repository.findById(appraisalId)
                .filter(appraisal -> appraisal.getMortgageApplicationId().equals(mortgageApplicationId))
                .flatMap(repository::delete);
    }
}