package com.firefly.core.lending.mortgages.core.services.property.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.property.v1.MortgagePropertyDTO;
import reactor.core.publisher.Mono;

public interface MortgagePropertyService {

    /**
     * Retrieves a paginated list of mortgage properties based on the provided filter criteria.
     *
     * @param filterRequest the filter criteria and pagination details for retrieving mortgage properties
     * @return a reactive wrapper containing a paginated response of mortgage properties
     */
    Mono<PaginationResponse<MortgagePropertyDTO>> findAll(FilterRequest<MortgagePropertyDTO> filterRequest);

    /**
     * Creates a new mortgage property record based on the provided data.
     *
     * @param dto the data transfer object containing details of the mortgage property to be created
     * @return a Mono emitting the created MortgagePropertyDTO instance upon successful creation
     */
    Mono<MortgagePropertyDTO> create(MortgagePropertyDTO dto);

    /**
     * Retrieves a mortgage property by its unique identifier.
     *
     * @param propertyId the unique identifier of the mortgage property to be retrieved
     * @return a Mono emitting the MortgagePropertyDTO corresponding to the specified ID,
     *         or an empty Mono if no property is found
     */
    Mono<MortgagePropertyDTO> getById(Long propertyId);

    /**
     * Updates an existing mortgage property with the provided details.
     *
     * @param propertyId the unique identifier of the mortgage property to be updated
     * @param dto the data transfer object containing the updated details of the mortgage property
     * @return a Mono emitting the updated MortgagePropertyDTO upon successful update, or an error if the update fails
     */
    Mono<MortgagePropertyDTO> update(Long propertyId, MortgagePropertyDTO dto);

    /**
     * Deletes a mortgage property identified by its unique property ID.
     *
     * @param propertyId the unique identifier of the mortgage property to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(Long propertyId);
}
