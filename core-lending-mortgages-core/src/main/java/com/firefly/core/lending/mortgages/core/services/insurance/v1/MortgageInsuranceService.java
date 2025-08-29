package com.firefly.core.lending.mortgages.core.services.insurance.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.insurance.v1.MortgageInsuranceDTO;
import reactor.core.publisher.Mono;

public interface MortgageInsuranceService {

    /**
     * Retrieves a paginated list of mortgage insurance records associated with a specific mortgage contract,
     * based on the provided filter criteria.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract whose insurance records are to be retrieved
     * @param filterRequest the filter criteria and pagination details for retrieving mortgage insurance records
     * @return a reactive wrapper containing a paginated response of MortgageInsuranceDTOs
     */
    Mono<PaginationResponse<MortgageInsuranceDTO>> findAll(Long mortgageContractId,
                                                           FilterRequest<MortgageInsuranceDTO> filterRequest);

    /**
     * Creates a new mortgage insurance record associated with the specified mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the insurance is associated
     * @param dto the data transfer object containing details of the mortgage insurance to be created
     * @return a Mono emitting the created MortgageInsuranceDTO instance upon successful creation
     */
    Mono<MortgageInsuranceDTO> create(Long mortgageContractId, MortgageInsuranceDTO dto);

    /**
     * Retrieves a specific mortgage insurance record based on the provided mortgage contract ID
     * and insurance ID.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract associated with the insurance
     * @param insuranceId the unique identifier of the insurance record to be retrieved
     * @return a Mono emitting the MortgageInsuranceDTO corresponding to the specified IDs,
     *         or an empty Mono if no insurance record is found
     */
    Mono<MortgageInsuranceDTO> getById(Long mortgageContractId, Long insuranceId);

    /**
     * Updates an existing mortgage insurance record associated with a specific mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the insurance belongs
     * @param insuranceId the unique identifier of the insurance record to be updated
     * @param dto the data transfer object containing the updated details of the mortgage insurance
     * @return a Mono emitting the updated MortgageInsuranceDTO upon successful update, or an error if the update operation fails
     */
    Mono<MortgageInsuranceDTO> update(Long mortgageContractId, Long insuranceId, MortgageInsuranceDTO dto);

    /**
     * Deletes a specific mortgage insurance record associated with a mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the insurance belongs
     * @param insuranceId the unique identifier of the insurance record to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(Long mortgageContractId, Long insuranceId);
}
