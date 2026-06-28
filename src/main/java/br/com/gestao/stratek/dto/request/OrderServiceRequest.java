package br.com.gestao.stratek.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderServiceRequest(

        UUID customerId,

        String channel,
        String priority,

        List<EquipmentRequest> equipments,
        List<OrderServiceItemRequest> serviceItems,
        List<OrderProductItemRequest> productItems,

        PaymentRequest payment

) {}
