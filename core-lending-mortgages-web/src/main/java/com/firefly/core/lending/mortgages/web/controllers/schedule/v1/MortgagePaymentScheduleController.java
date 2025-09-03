package com.firefly.core.lending.mortgages.web.controllers.schedule.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.schedule.v1.MortgagePaymentScheduleService;
import com.firefly.core.lending.mortgages.interfaces.dtos.schedule.v1.MortgagePaymentScheduleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import jakarta.validation.Valid;@RestController
@RequestMapping("/api/v1/mortgage-contracts/{mortgageContractId}/payment-schedule")
@Tag(name = "MortgagePaymentSchedule", description = "Scheduled installments for a mortgage contract")
@RequiredArgsConstructor
public class MortgagePaymentScheduleController {

    private final MortgagePaymentScheduleService service;

    @GetMapping
    @Operation(summary = "List or search the mortgage payment schedule for a contract")
    public Mono<ResponseEntity<PaginationResponse<MortgagePaymentScheduleDTO>>> findAll(
            @PathVariable UUID mortgageContractId,
            @ModelAttribute FilterRequest<MortgagePaymentScheduleDTO> filterRequest) {
        return service.findAll(mortgageContractId, filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new payment schedule entry")
    public Mono<ResponseEntity<MortgagePaymentScheduleDTO>> create(
            @PathVariable UUID mortgageContractId,
            @Valid @RequestBody MortgagePaymentScheduleDTO dto) {
        return service.create(mortgageContractId, dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{scheduleId}")
    @Operation(summary = "Get a specific payment schedule entry by ID")
    public Mono<ResponseEntity<MortgagePaymentScheduleDTO>> getById(
            @PathVariable UUID mortgageContractId,
            @PathVariable UUID scheduleId) {
        return service.getById(mortgageContractId, scheduleId).map(ResponseEntity::ok);
    }

    @PutMapping("/{scheduleId}")
    @Operation(summary = "Update a payment schedule entry")
    public Mono<ResponseEntity<MortgagePaymentScheduleDTO>> update(
            @PathVariable UUID mortgageContractId,
            @PathVariable UUID scheduleId,
            @Valid @RequestBody MortgagePaymentScheduleDTO dto) {
        return service.update(mortgageContractId, scheduleId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{scheduleId}")
    @Operation(summary = "Delete a payment schedule entry")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID mortgageContractId,
            @PathVariable UUID scheduleId) {
        return service.delete(mortgageContractId, scheduleId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
