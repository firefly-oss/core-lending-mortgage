package com.catalis.core.lending.mortgages.web.controllers.notification.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.core.services.notification.v1.MortgageNotificationService;
import com.catalis.core.lending.mortgages.interfaces.dtos.notification.v1.MortgageNotificationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/mortgage-contracts/{mortgageContractId}/notifications")
@Tag(name = "MortgageNotification", description = "Notifications or alerts related to a mortgage contract")
@RequiredArgsConstructor
public class MortgageNotificationController {

    private final MortgageNotificationService service;

    @GetMapping
    @Operation(summary = "List or search notifications for a mortgage contract")
    public Mono<ResponseEntity<PaginationResponse<MortgageNotificationDTO>>> findAll(
            @PathVariable Long mortgageContractId,
            @ModelAttribute FilterRequest<MortgageNotificationDTO> filterRequest) {
        return service.findAll(mortgageContractId, filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new notification record")
    public Mono<ResponseEntity<MortgageNotificationDTO>> create(
            @PathVariable Long mortgageContractId,
            @RequestBody MortgageNotificationDTO dto) {
        return service.create(mortgageContractId, dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{notificationId}")
    @Operation(summary = "Get a notification record by ID")
    public Mono<ResponseEntity<MortgageNotificationDTO>> getById(
            @PathVariable Long mortgageContractId,
            @PathVariable Long notificationId) {
        return service.getById(mortgageContractId, notificationId).map(ResponseEntity::ok);
    }

    @PutMapping("/{notificationId}")
    @Operation(summary = "Update a notification record")
    public Mono<ResponseEntity<MortgageNotificationDTO>> update(
            @PathVariable Long mortgageContractId,
            @PathVariable Long notificationId,
            @RequestBody MortgageNotificationDTO dto) {
        return service.update(mortgageContractId, notificationId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{notificationId}")
    @Operation(summary = "Delete a notification record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long mortgageContractId,
            @PathVariable Long notificationId) {
        return service.delete(mortgageContractId, notificationId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
