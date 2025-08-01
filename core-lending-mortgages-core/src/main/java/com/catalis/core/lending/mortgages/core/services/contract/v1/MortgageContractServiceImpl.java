package com.catalis.core.lending.mortgages.core.services.contract.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.models.entities.contract.v1.MortgageContract;
import com.catalis.core.lending.mortgages.models.repositories.contract.v1.MortgageContractRepository;
import com.catalis.core.lending.mortgages.core.mappers.contract.v1.MortgageContractMapper;
import com.catalis.core.lending.mortgages.interfaces.dtos.contract.v1.MortgageContractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

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
    public Mono<MortgageContractDTO> getById(Long mortgageContractId) {
        return repository.findById(mortgageContractId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<MortgageContractDTO> update(Long mortgageContractId, MortgageContractDTO dto) {
        return repository.findById(mortgageContractId)
                .flatMap(existingContract -> {
                    MortgageContract updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setMortgageContractId(existingContract.getMortgageContractId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long mortgageContractId) {
        return repository.findById(mortgageContractId)
                .flatMap(repository::delete);
    }
}
