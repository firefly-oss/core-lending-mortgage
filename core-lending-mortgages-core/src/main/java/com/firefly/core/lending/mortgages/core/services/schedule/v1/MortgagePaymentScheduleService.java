package com.firefly.core.lending.mortgages.core.services.schedule.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.schedule.v1.MortgagePaymentScheduleDTO;
import reactor.core.publisher.Mono;

public interface MortgagePaymentScheduleService {

    /**
     * Retrieves a paginated list of payment schedules associated with a specific mortgage contract
     * based on the provided filter criteria.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract whose payment schedules are to be retrieved
     * @param filterRequest the filter criteria and pagination details for retrieving payment schedules
     * @return a reactive wrapper containing a paginated response of MortgagePaymentScheduleDTOs
     */
    Mono<PaginationResponse<MortgagePaymentScheduleDTO>> findAll(Long mortgageContractId,
                                                                 FilterRequest<MortgagePaymentScheduleDTO> filterRequest);

    /**
     * Creates a new mortgage payment schedule entry for a specific mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the payment schedule belongs
     * @param dto the data transfer object containing the details of the mortgage payment schedule to be created
     * @return a Mono emitting the created MortgagePaymentScheduleDTO instance upon successful creation
     */
    Mono<MortgagePaymentScheduleDTO> create(Long mortgageContractId, MortgagePaymentScheduleDTO dto);

    /**
     * Retrieves a specific mortgage payment schedule associated with a given mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the payment schedule belongs
     * @param scheduleId the unique identifier of the payment schedule to be retrieved
     * @return a Mono emitting the MortgagePaymentScheduleDTO corresponding to the specified IDs,
     *         or an empty Mono if no schedule is found
     */
    Mono<MortgagePaymentScheduleDTO> getById(Long mortgageContractId, Long scheduleId);

    /**
     * Updates an existing mortgage payment schedule associated with a specific mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the schedule belongs
     * @param scheduleId the unique identifier of the mortgage payment schedule to be updated
     * @param dto the data transfer object containing the updated details of the mortgage payment schedule
     * @return a Mono emitting the updated MortgagePaymentScheduleDTO upon successful update,
     *         or an error if the update operation fails
     */
    Mono<MortgagePaymentScheduleDTO> update(Long mortgageContractId, Long scheduleId,
                                            MortgagePaymentScheduleDTO dto);

    /**
     * Deletes a specific mortgage payment schedule associated with a given mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the payment schedule belongs
     * @param scheduleId the unique identifier of the payment schedule to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(Long mortgageContractId, Long scheduleId);
}
