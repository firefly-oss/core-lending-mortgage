package com.catalis.core.lending.mortgages.core.services.disbursement.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.models.entities.disbursement.v1.MortgageDisbursement;
import com.catalis.core.lending.mortgages.models.repositories.disbursement.v1.MortgageDisbursementRepository;
import com.catalis.core.lending.mortgages.core.mappers.disbursement.v1.MortgageDisbursementMapper;
import com.catalis.core.lending.mortgages.interfaces.dtos.disbursement.v1.MortgageDisbursementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class MortgageDisbursementServiceImpl implements MortgageDisbursementService {

    @Autowired
    private MortgageDisbursementRepository repository;

    @Autowired
    private MortgageDisbursementMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgageDisbursementDTO>> findAll(Long mortgageContractId, FilterRequest<MortgageDisbursementDTO> filterRequest) {
        filterRequest.getFilters().setMortgageContractId(mortgageContractId);
        return FilterUtils.createFilter(
                MortgageDisbursement.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgageDisbursementDTO> create(Long mortgageContractId, MortgageDisbursementDTO dto) {
        dto.setMortgageContractId(mortgageContractId);
        MortgageDisbursement entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageDisbursementDTO> getById(Long mortgageContractId, Long mortgageDisbursementId) {
        return repository.findById(mortgageDisbursementId)
                .filter(entity -> mortgageContractId.equals(entity.getMortgageContractId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgageDisbursementDTO> update(Long mortgageContractId, Long mortgageDisbursementId, MortgageDisbursementDTO dto) {
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
    public Mono<Void> delete(Long mortgageContractId, Long mortgageDisbursementId) {
        return repository.findById(mortgageDisbursementId)
                .filter(entity -> mortgageContractId.equals(entity.getMortgageContractId()))
                .flatMap(repository::delete);
    }
}