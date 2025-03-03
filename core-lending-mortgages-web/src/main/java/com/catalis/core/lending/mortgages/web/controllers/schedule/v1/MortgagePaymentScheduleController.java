package com.catalis.core.lending.mortgages.web.controllers.schedule.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.core.services.schedule.v1.MortgagePaymentScheduleService;
import com.catalis.core.lending.mortgages.interfaces.dtos.schedule.v1.MortgagePaymentScheduleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/mortgage-contracts/{mortgageContractId}/payment-schedule")
@Tag(name = "MortgagePaymentSchedule", description = "Scheduled installments for a mortgage contract")
@RequiredArgsConstructor
public class MortgagePaymentScheduleController {

    private final MortgagePaymentScheduleService service;

    @GetMapping
    @Operation(summary = "List or search the mortgage payment schedule for a contract")
    public Mono<ResponseEntity<PaginationResponse<MortgagePaymentScheduleDTO>>> findAll(
            @PathVariable Long mortgageContractId,
            @ModelAttribute FilterRequest<MortgagePaymentScheduleDTO> filterRequest) {
        return service.findAll(mortgageContractId, filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new payment schedule entry")
    public Mono<ResponseEntity<MortgagePaymentScheduleDTO>> create(
            @PathVariable Long mortgageContractId,
            @RequestBody MortgagePaymentScheduleDTO dto) {
        return service.create(mortgageContractId, dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{scheduleId}")
    @Operation(summary = "Get a specific payment schedule entry by ID")
    public Mono<ResponseEntity<MortgagePaymentScheduleDTO>> getById(
            @PathVariable Long mortgageContractId,
            @PathVariable Long scheduleId) {
        return service.getById(mortgageContractId, scheduleId).map(ResponseEntity::ok);
    }

    @PutMapping("/{scheduleId}")
    @Operation(summary = "Update a payment schedule entry")
    public Mono<ResponseEntity<MortgagePaymentScheduleDTO>> update(
            @PathVariable Long mortgageContractId,
            @PathVariable Long scheduleId,
            @RequestBody MortgagePaymentScheduleDTO dto) {
        return service.update(mortgageContractId, scheduleId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{scheduleId}")
    @Operation(summary = "Delete a payment schedule entry")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long mortgageContractId,
            @PathVariable Long scheduleId) {
        return service.delete(mortgageContractId, scheduleId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
