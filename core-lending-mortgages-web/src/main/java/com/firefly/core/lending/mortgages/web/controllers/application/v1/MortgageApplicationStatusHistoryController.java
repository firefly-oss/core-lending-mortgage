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


package com.firefly.core.lending.mortgages.web.controllers.application.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.application.v1.MortgageApplicationStatusHistoryService;
import com.firefly.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationStatusHistoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/v1/mortgage-applications/{mortgageApplicationId}/status-history")
@Tag(name = "MortgageApplicationStatusHistory", description = "Status history logs for a mortgage application")
@RequiredArgsConstructor
public class MortgageApplicationStatusHistoryController {

    private final MortgageApplicationStatusHistoryService service;

    @GetMapping
    @Operation(summary = "List or search application status history")
    public Mono<ResponseEntity<PaginationResponse<MortgageApplicationStatusHistoryDTO>>> findAll(
            @PathVariable UUID mortgageApplicationId,
            @ModelAttribute FilterRequest<MortgageApplicationStatusHistoryDTO> filterRequest) {
        return service.findAll(mortgageApplicationId, filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new status change record")
    public Mono<ResponseEntity<MortgageApplicationStatusHistoryDTO>> create(
            @PathVariable UUID mortgageApplicationId,
            @Valid @RequestBody MortgageApplicationStatusHistoryDTO dto) {
        return service.create(mortgageApplicationId, dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{statusHistoryId}")
    @Operation(summary = "Get a status history record by ID")
    public Mono<ResponseEntity<MortgageApplicationStatusHistoryDTO>> getById(
            @PathVariable UUID mortgageApplicationId,
            @PathVariable UUID statusHistoryId) {
        return service.getById(mortgageApplicationId, statusHistoryId).map(ResponseEntity::ok);
    }

    @PutMapping("/{statusHistoryId}")
    @Operation(summary = "Update a status history record")
    public Mono<ResponseEntity<MortgageApplicationStatusHistoryDTO>> update(
            @PathVariable UUID mortgageApplicationId,
            @PathVariable UUID statusHistoryId,
            @Valid @RequestBody MortgageApplicationStatusHistoryDTO dto) {
        return service.update(mortgageApplicationId, statusHistoryId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{statusHistoryId}")
    @Operation(summary = "Delete a status history record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID mortgageApplicationId,
            @PathVariable UUID statusHistoryId) {
        return service.delete(mortgageApplicationId, statusHistoryId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
