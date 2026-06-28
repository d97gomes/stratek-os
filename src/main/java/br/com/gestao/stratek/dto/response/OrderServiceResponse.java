package br.com.gestao.stratek.dto.response;

import br.com.gestao.stratek.enums.OrderStatus;
import br.com.gestao.stratek.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderServiceResponse(

        UUID id,
        String number,

        UUID customerId,
        String customerName,

        OrderStatus status,
        PaymentStatus paymentStatus,

        LocalDateTime entryDateTime,
        LocalDateTime exitDateTime,

        String channel,
        String priority,

        BigDecimal totalProducts,
        BigDecimal totalServices,
        BigDecimal total,

        List<EquipmentResponse> equipments,
        List<OrderServiceItemResponse> serviceItems,
        List<OrderProductItemResponse> productItems,

        PaymentResponse payment,

        LocalDateTime createdAt

) {}