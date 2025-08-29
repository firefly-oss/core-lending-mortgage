package com.firefly.core.lending.mortgages.core.services.contract.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.contract.v1.MortgageContractDTO;
import reactor.core.publisher.Mono;

public interface MortgageContractService {

    /**
     * Retrieves a paginated list of mortgage contracts based on the provided filter criteria.
     *
     * @param filterRequest the filter criteria and pagination details for retrieving mortgage contracts
     * @return a reactive wrapper containing a paginated response of MortgageContractDTOs
     */
    Mono<PaginationResponse<MortgageContractDTO>> findAll(FilterRequest<MortgageContractDTO> filterRequest);

    /**
     * Creates a new mortgage contract based on the provided data.
     *
     * @param dto the data transfer object containing the details of the mortgage contract to be created
     * @return a Mono emitting the created MortgageContractDTO instance upon successful creation
     */
    Mono<MortgageContractDTO> create(MortgageContractDTO dto);

    /**
     * Retrieves a specific mortgage contract by its unique identifier.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to be retrieved
     * @return a Mono emitting the MortgageContractDTO corresponding to the specified ID,
     *         or an empty Mono if no contract is found
     */
    Mono<MortgageContractDTO> getById(Long mortgageContractId);

    /**
     * Updates an existing mortgage contract with the provided details.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to be updated
     * @param dto the data transfer object containing the updated details of the mortgage contract
     * @return a Mono emitting the updated MortgageContractDTO upon successful update,
     *         or an error if the update operation fails
     */
    Mono<MortgageContractDTO> update(Long mortgageContractId, MortgageContractDTO dto);

    /**
     * Deletes a mortgage contract identified by its unique ID.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(Long mortgageContractId);
}
