package br.com.gestao.stratek.controller;

import br.com.gestao.stratek.dto.request.ServiceRequest;
import br.com.gestao.stratek.dto.response.ServiceResponse;
import br.com.gestao.stratek.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService service;

    // ✅ CREATE
    @PostMapping
    public ServiceResponse create(@RequestBody ServiceRequest request) {
        return service.create(request);
    }

    // ✅ LIST
    @GetMapping
    public Page<ServiceResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    // ✅ FIND BY ID
    @GetMapping("/{id}")
    public ServiceResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ServiceResponse update(@PathVariable UUID id,
                                  @RequestBody ServiceRequest request) {
        return service.update(id, request);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}