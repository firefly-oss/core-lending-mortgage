package com.firefly.core.lending.mortgages.core.services.record.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.record.v1.MortgagePaymentRecordDTO;
import reactor.core.publisher.Mono;

public interface MortgagePaymentRecordService {

    /**
     * Retrieves a paginated list of mortgage payment records associated with a specific mortgage contract,
     * based on the provided filter criteria.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract whose payment records are to be retrieved
     * @param filterRequest the filter criteria and pagination details for retrieving mortgage payment records
     * @return a reactive wrapper containing a paginated response of MortgagePaymentRecordDTOs
     */
    Mono<PaginationResponse<MortgagePaymentRecordDTO>> findAll(Long mortgageContractId,
                                                               FilterRequest<MortgagePaymentRecordDTO> filterRequest);

    /**
     * Creates a new mortgage payment record associated with a specific mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the payment record belongs
     * @param dto the data transfer object containing the details of the mortgage payment record to be created
     * @return a Mono emitting the created MortgagePaymentRecordDTO instance upon successful creation
     */
    Mono<MortgagePaymentRecordDTO> create(Long mortgageContractId, MortgagePaymentRecordDTO dto);

    /**
     * Retrieves a specific mortgage payment record associated with a given mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract
     * @param paymentRecordId the unique identifier of the payment record to be retrieved
     * @return a Mono emitting the MortgagePaymentRecordDTO corresponding to the specified IDs,
     *         or an empty Mono if no record is found
     */
    Mono<MortgagePaymentRecordDTO> getById(Long mortgageContractId, Long paymentRecordId);

    /**
     * Updates an existing mortgage payment record associated with a specific mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the payment record belongs
     * @param paymentRecordId the unique identifier of the payment record to be updated
     * @param dto the data transfer object containing the updated details of the mortgage payment record
     * @return a Mono emitting the updated MortgagePaymentRecordDTO upon successful update,
     *         or an error if the update operation fails
     */
    Mono<MortgagePaymentRecordDTO> update(Long mortgageContractId, Long paymentRecordId,
                                          MortgagePaymentRecordDTO dto);

    /**
     * Deletes a specific payment record associated with a mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the payment record belongs
     * @param paymentRecordId the unique identifier of the payment record to be deleted
     * @return a {@code Mono<Void>} signaling the completion of the delete operation
     */
    Mono<Void> delete(Long mortgageContractId, Long paymentRecordId);
}
