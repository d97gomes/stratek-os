package br.com.gestao.stratek.service;

import br.com.gestao.stratek.dto.request.CreateWarrantyRequest;
import br.com.gestao.stratek.dto.request.UpdateWarrantyRequest;
import br.com.gestao.stratek.dto.response.WarrantyResponse;
import br.com.gestao.stratek.entity.OrderService;
import br.com.gestao.stratek.entity.Warranty;
import br.com.gestao.stratek.enums.OrderStatus;
import br.com.gestao.stratek.exception.ResourceNotFoundException;
import br.com.gestao.stratek.repository.OrderServiceRepository;
import br.com.gestao.stratek.repository.WarrantyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarrantyService {

    private final WarrantyRepository warrantyRepository;
    private final OrderServiceRepository orderRepository;

    public WarrantyResponse create(UUID orderId, CreateWarrantyRequest request) {

        // ✅ 1. Buscar OS
        OrderService order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("OS não encontrada"));

        // ✅ 2. Validar se já tem garantia
        warrantyRepository.findByOrderServiceId(orderId)
                .ifPresent(w -> {
                    throw new RuntimeException("Essa OS já possui garantia");
                });

        // ✅ 3. Validar status
        if (order.getStatus() != OrderStatus.COMPLETED
                && order.getStatus() != OrderStatus.DELIVERED) {
            throw new RuntimeException("A OS precisa estar finalizada para gerar garantia");
        }

        // ✅ 4. Validar data de saída
        if (order.getExitDateTime() == null) {
            throw new RuntimeException("A OS precisa ter data de saída");
        }

        // ✅ 5. Calcular datas
        LocalDateTime startDate = order.getExitDateTime();
        LocalDateTime endDate = startDate.plusMonths(3);

        // ✅ 6. Criar Warranty
        Warranty warranty = Warranty.builder()
                .orderService(order)
                .startDate(startDate)
                .endDate(endDate)
                .description(request.description())
                .problem(request.problem())
                .build();

        warranty = warrantyRepository.save(warranty);

        // ✅ 7. Retorno
        return new WarrantyResponse(
                warranty.getId(),
                order.getId(),
                warranty.getStartDate(),
                warranty.getEndDate(),
                warranty.getDescription(),
                warranty.getProblem(),
                warranty.getStatus(),
                warranty.getCreatedAt()
        );
    }

    // Get
    public WarrantyResponse findByOrderId(UUID orderId) {

        Warranty warranty = warrantyRepository.findByOrderServiceId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Garantia não encontrada"));

        return new WarrantyResponse(
                warranty.getId(),
                warranty.getOrderService().getId(),
                warranty.getStartDate(),
                warranty.getEndDate(),
                warranty.getDescription(),
                warranty.getProblem(),
                warranty.getStatus(),
                warranty.getCreatedAt()
        );
    }

    //Delete logico
    public void cancel(UUID id) {

        Warranty warranty = warrantyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Garantia não encontrada"));

        warranty.setStatus(br.com.gestao.stratek.enums.WarrantyStatus.VOIDED);

        warrantyRepository.save(warranty);
    }

    //update
    public WarrantyResponse update(UUID id, UpdateWarrantyRequest request) {

        // 🔹 1. Buscar garantia
        Warranty warranty = warrantyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Garantia não encontrada"));

        // 🔹 2. Atualizar campos
        if (request.description() != null) {
            warranty.setDescription(request.description());
        }

        if (request.problem() != null) {
            warranty.setProblem(request.problem());
        }

        // 🔹 3. salvar
        warranty = warrantyRepository.save(warranty);

        // 🔹 4. retorno
        return new WarrantyResponse(
                warranty.getId(),
                warranty.getOrderService().getId(),
                warranty.getStartDate(),
                warranty.getEndDate(),
                warranty.getDescription(),
                warranty.getProblem(),
                warranty.getStatus(),
                warranty.getCreatedAt()
        );
    }

}
