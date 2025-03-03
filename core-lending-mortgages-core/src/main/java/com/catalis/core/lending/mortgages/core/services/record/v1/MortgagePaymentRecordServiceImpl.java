package com.catalis.core.lending.mortgages.core.services.record.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.models.entities.record.v1.MortgagePaymentRecord;
import com.catalis.core.lending.mortgages.models.repositories.record.v1.MortgagePaymentRecordRepository;
import com.catalis.core.lending.mortgages.core.mappers.record.v1.MortgagePaymentRecordMapper;
import com.catalis.core.lending.mortgages.interfaces.dtos.record.v1.MortgagePaymentRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class MortgagePaymentRecordServiceImpl implements MortgagePaymentRecordService {

    @Autowired
    private MortgagePaymentRecordRepository repository;

    @Autowired
    private MortgagePaymentRecordMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgagePaymentRecordDTO>> findAll(Long mortgageContractId, FilterRequest<MortgagePaymentRecordDTO> filterRequest) {
        filterRequest.getFilters().setMortgageContractId(mortgageContractId);
        return FilterUtils.createFilter(
                MortgagePaymentRecord.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgagePaymentRecordDTO> create(Long mortgageContractId, MortgagePaymentRecordDTO dto) {
        dto.setMortgageContractId(mortgageContractId);
        MortgagePaymentRecord entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgagePaymentRecordDTO> getById(Long mortgageContractId, Long paymentRecordId) {
        return repository.findById(paymentRecordId)
                .filter(record -> record.getMortgageContractId().equals(mortgageContractId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgagePaymentRecordDTO> update(Long mortgageContractId, Long paymentRecordId, MortgagePaymentRecordDTO dto) {
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
    public Mono<Void> delete(Long mortgageContractId, Long paymentRecordId) {
        return repository.findById(paymentRecordId)
                .filter(record -> record.getMortgageContractId().equals(mortgageContractId))
                .flatMap(repository::delete);
    }
}