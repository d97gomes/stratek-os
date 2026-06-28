package br.com.gestao.stratek.controller;

import br.com.gestao.stratek.dto.request.CustomerRequest;
import br.com.gestao.stratek.dto.response.CustomerResponse;
import br.com.gestao.stratek.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    // ✅ CREATE
    @PostMapping
    public CustomerResponse create(@RequestBody @Valid CustomerRequest request) {
        return service.create(request);
    }

    // ✅ LIST (com paginação)
    @GetMapping
    public Page<CustomerResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    // ✅ FIND BY ID
    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable UUID id,
                                   @RequestBody @Valid CustomerRequest request) {
        return service.update(id, request);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}