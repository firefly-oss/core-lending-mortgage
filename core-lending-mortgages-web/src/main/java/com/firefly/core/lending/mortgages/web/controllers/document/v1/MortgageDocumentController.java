package com.firefly.core.lending.mortgages.web.controllers.document.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.document.v1.MortgageDocumentService;
import com.firefly.core.lending.mortgages.interfaces.dtos.document.v1.MortgageDocumentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/mortgage-applications/{mortgageApplicationId}/documents")
@Tag(name = "MortgageDocument", description = "Documents provided for a mortgage application")
@RequiredArgsConstructor
public class MortgageDocumentController {

    private final MortgageDocumentService service;

    @GetMapping
    @Operation(summary = "List or search documents for a mortgage application")
    public Mono<ResponseEntity<PaginationResponse<MortgageDocumentDTO>>> findAll(
            @PathVariable Long mortgageApplicationId,
            @ModelAttribute FilterRequest<MortgageDocumentDTO> filterRequest) {
        return service.findAll(mortgageApplicationId, filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new document record for an application")
    public Mono<ResponseEntity<MortgageDocumentDTO>> create(
            @PathVariable Long mortgageApplicationId,
            @RequestBody MortgageDocumentDTO dto) {
        return service.create(mortgageApplicationId, dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{documentId}")
    @Operation(summary = "Get a document record by ID")
    public Mono<ResponseEntity<MortgageDocumentDTO>> getById(
            @PathVariable Long mortgageApplicationId,
            @PathVariable Long documentId) {
        return service.getById(mortgageApplicationId, documentId).map(ResponseEntity::ok);
    }

    @PutMapping("/{documentId}")
    @Operation(summary = "Update a mortgage document record")
    public Mono<ResponseEntity<MortgageDocumentDTO>> update(
            @PathVariable Long mortgageApplicationId,
            @PathVariable Long documentId,
            @RequestBody MortgageDocumentDTO dto) {
        return service.update(mortgageApplicationId, documentId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{documentId}")
    @Operation(summary = "Delete a mortgage document record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long mortgageApplicationId,
            @PathVariable Long documentId) {
        return service.delete(mortgageApplicationId, documentId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
