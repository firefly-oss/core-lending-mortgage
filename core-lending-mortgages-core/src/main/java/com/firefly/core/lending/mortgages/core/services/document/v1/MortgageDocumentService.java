/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.mortgages.core.services.document.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.document.v1.MortgageDocumentDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;
public interface MortgageDocumentService {

    /**
     * Retrieves a paginated list of mortgage documents associated with a specific mortgage application,
     * based on the provided filter criteria.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application whose documents are to be retrieved
     * @param filterRequest the filter criteria and pagination details for retrieving mortgage documents
     * @return a reactive wrapper containing a paginated response of MortgageDocumentDTOs
     */
    Mono<PaginationResponse<MortgageDocumentDTO>> findAll(UUID mortgageApplicationId,
                                                          FilterRequest<MortgageDocumentDTO> filterRequest);

    /**
     * Creates a new mortgage document associated with the specified mortgage application.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to which the document belongs
     * @param dto the data transfer object containing details of the mortgage document to be created
     * @return a Mono emitting the created MortgageDocumentDTO instance upon successful creation
     */
    Mono<MortgageDocumentDTO> create(UUID mortgageApplicationId, MortgageDocumentDTO dto);

    /**
     *
     */
    Mono<MortgageDocumentDTO> getById(UUID mortgageApplicationId, UUID documentId);

    /**
     * Updates an existing mortgage document associated with a specific mortgage application.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to which the document belongs
     * @param documentId the unique identifier of the document to be updated
     * @param dto the data transfer object containing the updated details of the mortgage document
     * @return a Mono emitting the updated MortgageDocumentDTO upon successful update, or an error if the update operation fails
     */
    Mono<MortgageDocumentDTO> update(UUID mortgageApplicationId, UUID documentId, MortgageDocumentDTO dto);

    /**
     * Deletes a specific document associated with a mortgage application.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to which the document belongs
     * @param documentId the unique identifier of the document to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(UUID mortgageApplicationId, UUID documentId);
}