package com.firefly.core.lending.mortgages.core.services.notification.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.notification.v1.MortgageNotificationDTO;
import reactor.core.publisher.Mono;

public interface MortgageNotificationService {

    /**
     * Retrieves a paginated list of mortgage notifications associated with a specific mortgage contract,
     * based on the provided filter criteria.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract whose notifications are to be retrieved
     * @param filterRequest the filter criteria and pagination details for retrieving mortgage notifications
     * @return a reactive wrapper containing a paginated response of MortgageNotificationDTOs
     */
    Mono<PaginationResponse<MortgageNotificationDTO>> findAll(Long mortgageContractId,
                                                              FilterRequest<MortgageNotificationDTO> filterRequest);

    /**
     * Creates a new mortgage notification associated with the specified mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the notification belongs
     * @param dto the data transfer object containing details of the mortgage notification to be created
     * @return a Mono emitting the created MortgageNotificationDTO instance upon successful creation
     */
    Mono<MortgageNotificationDTO> create(Long mortgageContractId, MortgageNotificationDTO dto);

    /**
     * Retrieves a specific mortgage notification by its unique identifier within a given mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract associated with the notification
     * @param notificationId the unique identifier of the notification to be retrieved
     * @return a Mono emitting the MortgageNotificationDTO corresponding to the specified identifiers,
     *         or an empty Mono if no notification is found
     */
    Mono<MortgageNotificationDTO> getById(Long mortgageContractId, Long notificationId);

    /**
     * Updates an existing mortgage notification associated with a specified mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the notification belongs
     * @param notificationId the unique identifier of the notification to be updated
     * @param dto the data transfer object containing the updated details of the mortgage notification
     * @return a Mono emitting the updated MortgageNotificationDTO upon successful update,
     *         or an error if the update operation fails
     */
    Mono<MortgageNotificationDTO> update(Long mortgageContractId, Long notificationId,
                                         MortgageNotificationDTO dto);

    /**
     * Deletes a specific mortgage notification identified by its unique identifiers.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the notification belongs
     * @param notificationId the unique identifier of the notification to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(Long mortgageContractId, Long notificationId);
}
