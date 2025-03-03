package com.catalis.core.lending.mortgages.web.controllers.application.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.core.services.application.v1.MortgageApplicationStatusHistoryService;
import com.catalis.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationStatusHistoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/mortgage-applications/{mortgageApplicationId}/status-history")
@Tag(name = "MortgageApplicationStatusHistory", description = "Status history logs for a mortgage application")
@RequiredArgsConstructor
public class MortgageApplicationStatusHistoryController {

    private final MortgageApplicationStatusHistoryService service;

    @GetMapping
    @Operation(summary = "List or search application status history")
    public Mono<ResponseEntity<PaginationResponse<MortgageApplicationStatusHistoryDTO>>> findAll(
            @PathVariable Long mortgageApplicationId,
            @ModelAttribute FilterRequest<MortgageApplicationStatusHistoryDTO> filterRequest) {
        return service.findAll(mortgageApplicationId, filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new status change record")
    public Mono<ResponseEntity<MortgageApplicationStatusHistoryDTO>> create(
            @PathVariable Long mortgageApplicationId,
            @RequestBody MortgageApplicationStatusHistoryDTO dto) {
        return service.create(mortgageApplicationId, dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{statusHistoryId}")
    @Operation(summary = "Get a status history record by ID")
    public Mono<ResponseEntity<MortgageApplicationStatusHistoryDTO>> getById(
            @PathVariable Long mortgageApplicationId,
            @PathVariable Long statusHistoryId) {
        return service.getById(mortgageApplicationId, statusHistoryId).map(ResponseEntity::ok);
    }

    @PutMapping("/{statusHistoryId}")
    @Operation(summary = "Update a status history record")
    public Mono<ResponseEntity<MortgageApplicationStatusHistoryDTO>> update(
            @PathVariable Long mortgageApplicationId,
            @PathVariable Long statusHistoryId,
            @RequestBody MortgageApplicationStatusHistoryDTO dto) {
        return service.update(mortgageApplicationId, statusHistoryId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{statusHistoryId}")
    @Operation(summary = "Delete a status history record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long mortgageApplicationId,
            @PathVariable Long statusHistoryId) {
        return service.delete(mortgageApplicationId, statusHistoryId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
