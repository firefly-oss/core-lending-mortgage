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

import java.util.UUID;

import jakarta.validation.Valid;@RestController
@RequestMapping("/api/v1/mortgage-contracts/{mortgageContractId}/payment-records")
@Tag(name = "MortgagePaymentRecord", description = "Payments made toward a mortgage contract")
@RequiredArgsConstructor
public class MortgagePaymentRecordController {

    private final MortgagePaymentRecordService service;

    @GetMapping
    @Operation(summary = "List or search mortgage payment records for a contract")
    public Mono<ResponseEntity<PaginationResponse<MortgagePaymentRecordDTO>>> findAll(
            @PathVariable UUID mortgageContractId,
            @ModelAttribute FilterRequest<MortgagePaymentRecordDTO> filterRequest) {
        return service.findAll(mortgageContractId, filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new payment record")
    public Mono<ResponseEntity<MortgagePaymentRecordDTO>> create(
            @PathVariable UUID mortgageContractId,
            @Valid @RequestBody MortgagePaymentRecordDTO dto) {
        return service.create(mortgageContractId, dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{paymentRecordId}")
    @Operation(summary = "Get a payment record by ID")
    public Mono<ResponseEntity<MortgagePaymentRecordDTO>> getById(
            @PathVariable UUID mortgageContractId,
            @PathVariable UUID paymentRecordId) {
        return service.getById(mortgageContractId, paymentRecordId).map(ResponseEntity::ok);
    }

    @PutMapping("/{paymentRecordId}")
    @Operation(summary = "Update a payment record")
    public Mono<ResponseEntity<MortgagePaymentRecordDTO>> update(
            @PathVariable UUID mortgageContractId,
            @PathVariable UUID paymentRecordId,
            @Valid @RequestBody MortgagePaymentRecordDTO dto) {
        return service.update(mortgageContractId, paymentRecordId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{paymentRecordId}")
    @Operation(summary = "Delete a payment record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID mortgageContractId,
            @PathVariable UUID paymentRecordId) {
        return service.delete(mortgageContractId, paymentRecordId).
                thenReturn(ResponseEntity.noContent().build());
    }
}
