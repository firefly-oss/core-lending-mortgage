package com.catalis.core.lending.mortgages.core.services.schedule.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.models.entities.schedule.v1.MortgagePaymentSchedule;
import com.catalis.core.lending.mortgages.models.repositories.schedule.v1.MortgagePaymentScheduleRepository;
import com.catalis.core.lending.mortgages.core.mappers.schedule.v1.MortgagePaymentScheduleMapper;
import com.catalis.core.lending.mortgages.interfaces.dtos.schedule.v1.MortgagePaymentScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class MortgagePaymentScheduleServiceImpl implements MortgagePaymentScheduleService {

    @Autowired
    private MortgagePaymentScheduleRepository repository;

    @Autowired
    private MortgagePaymentScheduleMapper mapper;

    @Override
    public Mono<PaginationResponse<MortgagePaymentScheduleDTO>> findAll(Long mortgageContractId, FilterRequest<MortgagePaymentScheduleDTO> filterRequest) {
        filterRequest.getFilters().setMortgageContractId(mortgageContractId);
        return FilterUtils.createFilter(
                MortgagePaymentSchedule.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<MortgagePaymentScheduleDTO> create(Long mortgageContractId, MortgagePaymentScheduleDTO dto) {
        dto.setMortgageContractId(mortgageContractId);
        MortgagePaymentSchedule entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgagePaymentScheduleDTO> getById(Long mortgageContractId, Long scheduleId) {
        return repository.findById(scheduleId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<MortgagePaymentScheduleDTO> update(Long mortgageContractId, Long scheduleId, MortgagePaymentScheduleDTO dto) {
        return repository.findById(scheduleId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .flatMap(entity -> {
                    MortgagePaymentSchedule updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setScheduleId(scheduleId);
                    updatedEntity.setMortgageContractId(mortgageContractId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long mortgageContractId, Long scheduleId) {
        return repository.findById(scheduleId)
                .filter(entity -> entity.getMortgageContractId().equals(mortgageContractId))
                .flatMap(repository::delete);
    }
}
