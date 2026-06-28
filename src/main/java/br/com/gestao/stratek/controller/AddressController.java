package br.com.gestao.stratek.controller;

import br.com.gestao.stratek.dto.request.AddressRequest;
import br.com.gestao.stratek.dto.response.AddressResponse;
import br.com.gestao.stratek.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @PostMapping
    public AddressResponse create(@RequestBody @Valid AddressRequest request) {
        return service.create(request);
    }

    @GetMapping
    public Page<AddressResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public AddressResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public AddressResponse update(@PathVariable UUID id,
                                  @RequestBody @Valid AddressRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}