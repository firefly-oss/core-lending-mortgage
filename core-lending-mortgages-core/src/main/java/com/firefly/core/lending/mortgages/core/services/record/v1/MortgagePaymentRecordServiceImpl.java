package com.firefly.core.lending.mortgages.core.services.record.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.models.entities.record.v1.MortgagePaymentRecord;
import com.firefly.core.lending.mortgages.models.repositories.record.v1.MortgagePaymentRecordRepository;
import com.firefly.core.lending.mortgages.core.mappers.record.v1.MortgagePaymentRecordMapper;
import com.firefly.core.lending.mortgages.interfaces.dtos.record.v1.MortgagePaymentRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@Transactional
public class MortgagePaymentRecordServiceImpl implements MortgagePaymentRecordService {

    @Autowired
    private MortgagePaymentRecordRepository repository;

    @Autowired
    private MortgagePaymentRecordMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgagePaymentRecordDTO>> findAll(UUID mortgageContractId, FilterRequest<MortgagePaymentRecordDTO> filterRequest) {
        filterRequest.getFilters().setMortgageContractId(mortgageContractId);
        return FilterUtils.createFilter(
                MortgagePaymentRecord.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgagePaymentRecordDTO> create(UUID mortgageContractId, MortgagePaymentRecordDTO dto) {
        dto.setMortgageContractId(mortgageContractId);
        MortgagePaymentRecord entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgagePaymentRecordDTO> getById(UUID mortgageContractId, UUID paymentRecordId) {
        return repository.findById(paymentRecordId)
                .filter(record -> record.getMortgageContractId().equals(mortgageContractId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgagePaymentRecordDTO> update(UUID mortgageContractId, UUID paymentRecordId, MortgagePaymentRecordDTO dto) {
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
    public Mono<Void> delete(UUID mortgageContractId, UUID paymentRecordId) {
        return repository.findById(paymentRecordId)
                .filter(record -> record.getMortgageContractId().equals(mortgageContractId))
                .flatMap(repository::delete);
    }
}