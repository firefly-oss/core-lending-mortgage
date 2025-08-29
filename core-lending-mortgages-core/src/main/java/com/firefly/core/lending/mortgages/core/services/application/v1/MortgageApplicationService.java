package com.firefly.core.lending.mortgages.core.services.application.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationDTO;
import reactor.core.publisher.Mono;

public interface MortgageApplicationService {

    /**
     * Retrieves a paginated list of mortgage applications based on the provided filter criteria.
     *
     * @param filterRequest the filter criteria and pagination details for retrieving mortgage applications
     * @return a reactive wrapper containing a paginated response of mortgage applications
     */
    Mono<PaginationResponse<MortgageApplicationDTO>> findAll(FilterRequest<MortgageApplicationDTO> filterRequest);

    /**
     * Creates a new mortgage application based on the provided data.
     *
     * @param dto the data transfer object containing details of the mortgage application to be created
     * @return a Mono emitting the created MortgageApplicationDTO instance
     */
    Mono<MortgageApplicationDTO> create(MortgageApplicationDTO dto);

    /**
     * Retrieves a mortgage application by its unique identifier.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application
     * @return a Mono emitting the MortgageApplicationDTO corresponding to the given identifier,
     *         or an empty Mono if no mortgage application is found
     */
    Mono<MortgageApplicationDTO> getById(Long mortgageApplicationId);

    /**
     * Updates an existing mortgage application with the provided data.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to be updated
     * @param dto the data transfer object containing the updated details of the mortgage application
     * @return a Mono emitting the updated MortgageApplicationDTO upon successful update, or an error if the update fails
     */
    Mono<MortgageApplicationDTO> update(Long mortgageApplicationId, MortgageApplicationDTO dto);

    /**
     * Deletes a mortgage application identified by its ID.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to be deleted
     * @return a {@code Mono<Void>} indicating completion of the delete operation
     */
    Mono<Void> delete(Long mortgageApplicationId);
}
