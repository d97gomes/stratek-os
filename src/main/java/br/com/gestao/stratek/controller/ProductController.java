package br.com.gestao.stratek.controller;

import br.com.gestao.stratek.dto.request.ProductRequest;
import br.com.gestao.stratek.dto.response.ProductResponse;
import br.com.gestao.stratek.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    // ✅ CREATE
    @PostMapping
    public ProductResponse create(@RequestBody @Valid ProductRequest request) {
        return service.create(request);
    }

    // ✅ LIST (com paginação 🔥)
    @GetMapping
    public Page<ProductResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    // ✅ FIND BY ID
    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable UUID id,
                                  @RequestBody @Valid ProductRequest request) {
        return service.update(id, request);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}