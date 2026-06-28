package br.com.gestao.stratek.controller;

import br.com.gestao.stratek.dto.request.OrderServiceRequest;
import br.com.gestao.stratek.dto.request.UpdateOrderServiceRequest;
import br.com.gestao.stratek.dto.request.UpdateOrderStatusRequest;
import br.com.gestao.stratek.dto.request.UpdatePaymentRequest;
import br.com.gestao.stratek.dto.response.OrderServiceResponse;
import br.com.gestao.stratek.service.OrderServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderServiceController {

    private final OrderServiceService service;

    // ✅ CREATE OS
    @PostMapping
    public OrderServiceResponse create(@RequestBody @Valid OrderServiceRequest request) {
        return service.create(request);
    }

    // ✅ LISTAR COM PAGINAÇÃO
    @GetMapping
    public Page<OrderServiceResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    // ✅ BUSCAR POR ID
    @GetMapping("/{id}")
    public OrderServiceResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    // update status
    @PutMapping("/{id}/status")
    public OrderServiceResponse updateStatus(@PathVariable UUID id,
                                             @RequestBody UpdateOrderStatusRequest request) {
        return service.updateStatus(id, request.status());
    }

    // update payment
    @PutMapping("/{id}/payment")
    public OrderServiceResponse updatePayment(@PathVariable UUID id,
                                              @RequestBody UpdatePaymentRequest request) {
        return service.updatePayment(id, request);
    }

    // update channel e priority
    @PutMapping("/{id}")
    public OrderServiceResponse update(@PathVariable UUID id,
                                                                @RequestBody UpdateOrderServiceRequest request) {
        return service.update(id, request);
    }


}
