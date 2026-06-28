package br.com.gestao.stratek.controller;

import br.com.gestao.stratek.dto.request.CreateWarrantyRequest;
import br.com.gestao.stratek.dto.request.UpdateWarrantyRequest;
import br.com.gestao.stratek.dto.response.WarrantyResponse;
import br.com.gestao.stratek.service.WarrantyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class WarrantyController {

    private final WarrantyService service;

    @PostMapping("/{id}/warranty")
    public WarrantyResponse create(@PathVariable UUID id,
                                   @RequestBody CreateWarrantyRequest request) {
        return service.create(id, request);
    }

    @GetMapping("/{id}/warranty")
    public WarrantyResponse findByOrder(@PathVariable UUID id) {
        return service.findByOrderId(id);
    }

    @PutMapping("/warranty/{id}/cancel")
    public void cancel(@PathVariable UUID id) {
        service.cancel(id);
    }

    @PutMapping("/warranty/{id}")
    public WarrantyResponse update(@PathVariable UUID id,
                                   @RequestBody UpdateWarrantyRequest request) {
        return service.update(id, request);
    }
}