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

@Service
@Transactional
public class MortgageInsuranceServiceImpl implements MortgageInsuranceService {

    @Autowired
    private MortgageInsuranceRepository repository;

    @Autowired
    private MortgageInsuranceMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageInsuranceDTO>> findAll(Long mortgageContractId, FilterRequest<MortgageInsuranceDTO> filterRequest) {
        filterRequest.getFilters().setMortgageContractId(mortgageContractId);
        return FilterUtils.createFilter(
                MortgageInsurance.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageInsuranceDTO> create(Long mortgageContractId, MortgageInsuranceDTO dto) {
        dto.setMortgageContractId(mortgageContractId);
        MortgageInsurance entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageInsuranceDTO> getById(Long mortgageContractId, Long insuranceId) {
        return repository.findById(insuranceId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageInsuranceDTO> update(Long mortgageContractId, Long insuranceId, MortgageInsuranceDTO dto) {
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
    public Mono<Void> delete(Long mortgageContractId, Long insuranceId) {
        return repository.findById(insuranceId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .flatMap(repository::delete);
    }
}
