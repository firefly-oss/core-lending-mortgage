package com.firefly.core.lending.mortgages.web.controllers.record.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.record.v1.MortgagePaymentRecordService;
import com.firefly.core.lending.mortgages.interfaces.dtos.record.v1.MortgagePaymentRecordDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/mortgage-contracts/{mortgageContractId}/payment-records")
@Tag(name = "MortgagePaymentRecord", description = "Payments made toward a mortgage contract")
@RequiredArgsConstructor
public class MortgagePaymentRecordController {

    private final MortgagePaymentRecordService service;

    @GetMapping
    @Operation(summary = "List or search mortgage payment records for a contract")
    public Mono<ResponseEntity<PaginationResponse<MortgagePaymentRecordDTO>>> findAll(
            @PathVariable Long mortgageContractId,
            @ModelAttribute FilterRequest<MortgagePaymentRecordDTO> filterRequest) {
        return service.findAll(mortgageContractId, filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new payment record")
    public Mono<ResponseEntity<MortgagePaymentRecordDTO>> create(
            @PathVariable Long mortgageContractId,
            @RequestBody MortgagePaymentRecordDTO dto) {
        return service.create(mortgageContractId, dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{paymentRecordId}")
    @Operation(summary = "Get a payment record by ID")
    public Mono<ResponseEntity<MortgagePaymentRecordDTO>> getById(
            @PathVariable Long mortgageContractId,
            @PathVariable Long paymentRecordId) {
        return service.getById(mortgageContractId, paymentRecordId).map(ResponseEntity::ok);
    }

    @PutMapping("/{paymentRecordId}")
    @Operation(summary = "Update a payment record")
    public Mono<ResponseEntity<MortgagePaymentRecordDTO>> update(
            @PathVariable Long mortgageContractId,
            @PathVariable Long paymentRecordId,
            @RequestBody MortgagePaymentRecordDTO dto) {
        return service.update(mortgageContractId, paymentRecordId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{paymentRecordId}")
    @Operation(summary = "Delete a payment record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long mortgageContractId,
            @PathVariable Long paymentRecordId) {
        return service.delete(mortgageContractId, paymentRecordId).
                thenReturn(ResponseEntity.noContent().build());
    }
}
