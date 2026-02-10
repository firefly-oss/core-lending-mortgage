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


package com.firefly.core.lending.mortgages.web.controllers;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.MortgageAgreementService;
import com.firefly.core.lending.mortgages.interfaces.dtos.MortgageAgreementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import jakarta.validation.Valid;@RestController
@RequestMapping("/api/v1/mortgage-agreements")
@Tag(name = "MortgageAgreement", description = "Operations on mortgage agreements")
@RequiredArgsConstructor
public class MortgageAgreementController {

    private final MortgageAgreementService service;

    @GetMapping
    @Operation(summary = "List or search mortgage agreements")
    public Mono<ResponseEntity<PaginationResponse<MortgageAgreementDTO>>> findAll(
            @Valid @RequestBody FilterRequest<MortgageAgreementDTO> filterRequest) {
        return service.findAll(filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new mortgage agreement")
    public Mono<ResponseEntity<MortgageAgreementDTO>> create(
            @Valid @RequestBody MortgageAgreementDTO dto) {
        return service.create(dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{mortgageAgreementId}")
    @Operation(summary = "Get a mortgage agreement by ID")
    public Mono<ResponseEntity<MortgageAgreementDTO>> getById(
            @PathVariable UUID mortgageAgreementId) {
        return service.getById(mortgageAgreementId).map(ResponseEntity::ok);
    }

    @PutMapping("/{mortgageAgreementId}")
    @Operation(summary = "Update a mortgage agreement")
    public Mono<ResponseEntity<MortgageAgreementDTO>> update(
            @PathVariable UUID mortgageAgreementId,
            @Valid @RequestBody MortgageAgreementDTO dto) {
        return service.update(mortgageAgreementId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{mortgageAgreementId}")
    @Operation(summary = "Delete a mortgage agreement")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID mortgageAgreementId) {
        return service.delete(mortgageAgreementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
