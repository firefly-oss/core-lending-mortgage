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


package com.firefly.core.lending.mortgages.core.services.application.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationStatusHistoryDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;
public interface MortgageApplicationStatusHistoryService {

    /**
     * Retrieves a paginated list of Mortgage Application Status History records based on the provided mortgage application ID
     * and filter criteria.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application whose status history is to be retrieved
     * @param filterRequest the filter criteria and pagination details for retrieving status history records
     * @return a reactive wrapper containing a paginated response of Mortgage Application Status History DTOs
     */
    Mono<PaginationResponse<MortgageApplicationStatusHistoryDTO>> findAll(
            UUID mortgageApplicationId,
            FilterRequest<MortgageApplicationStatusHistoryDTO> filterRequest);

    /**
     * Creates a new status history record for a specific mortgage application.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to which the status history belongs
     * @param dto the data transfer object containing details of the status history to be created
     * @return a Mono emitting the created MortgageApplicationStatusHistoryDTO object upon successful creation, or an error if the operation fails
     */
    Mono<MortgageApplicationStatusHistoryDTO> create(UUID mortgageApplicationId,
                                                     MortgageApplicationStatusHistoryDTO dto);

    /**
     * Retrieves a specific status history record for a given mortgage application by its unique identifiers.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application
     * @param statusHistoryId the unique identifier of the status history record
     * @return a Mono emitting the MortgageApplicationStatusHistoryDTO corresponding to the given identifiers,
     *         or an empty Mono if no matching record is found
     */
    Mono<MortgageApplicationStatusHistoryDTO> getById(UUID mortgageApplicationId,
                                                      UUID statusHistoryId);

    /**
     * Updates the specific status history entry of a mortgage application with the provided details.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to which the status history belongs
     * @param statusHistoryId the unique identifier of the status history entry to be updated
     * @param dto the data transfer object containing the updated details for the status history entry
     * @return a Mono emitting the updated MortgageApplicationStatusHistoryDTO object upon a successful update,
     *         or an error if the update operation fails
     */
    Mono<MortgageApplicationStatusHistoryDTO> update(UUID mortgageApplicationId,
                                                     UUID statusHistoryId,
                                                     MortgageApplicationStatusHistoryDTO dto);

    /**
     * Deletes a specific mortgage application status history entry based on the provided identifiers.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application
     * @param statusHistoryId the unique identifier of the status history entry to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(UUID mortgageApplicationId, UUID statusHistoryId);
}