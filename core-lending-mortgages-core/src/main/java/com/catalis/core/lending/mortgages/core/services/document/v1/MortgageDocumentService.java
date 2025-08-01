package com.catalis.core.lending.mortgages.core.services.document.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.interfaces.dtos.document.v1.MortgageDocumentDTO;
import reactor.core.publisher.Mono;

public interface MortgageDocumentService {

    /**
     * Retrieves a paginated list of mortgage documents associated with a specific mortgage application,
     * based on the provided filter criteria.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application whose documents are to be retrieved
     * @param filterRequest the filter criteria and pagination details for retrieving mortgage documents
     * @return a reactive wrapper containing a paginated response of MortgageDocumentDTOs
     */
    Mono<PaginationResponse<MortgageDocumentDTO>> findAll(Long mortgageApplicationId,
                                                          FilterRequest<MortgageDocumentDTO> filterRequest);

    /**
     * Creates a new mortgage document associated with the specified mortgage application.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to which the document belongs
     * @param dto the data transfer object containing details of the mortgage document to be created
     * @return a Mono emitting the created MortgageDocumentDTO instance upon successful creation
     */
    Mono<MortgageDocumentDTO> create(Long mortgageApplicationId, MortgageDocumentDTO dto);

    /**
     *
     */
    Mono<MortgageDocumentDTO> getById(Long mortgageApplicationId, Long documentId);

    /**
     * Updates an existing mortgage document associated with a specific mortgage application.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to which the document belongs
     * @param documentId the unique identifier of the document to be updated
     * @param dto the data transfer object containing the updated details of the mortgage document
     * @return a Mono emitting the updated MortgageDocumentDTO upon successful update, or an error if the update operation fails
     */
    Mono<MortgageDocumentDTO> update(Long mortgageApplicationId, Long documentId, MortgageDocumentDTO dto);

    /**
     * Deletes a specific document associated with a mortgage application.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to which the document belongs
     * @param documentId the unique identifier of the document to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(Long mortgageApplicationId, Long documentId);
}