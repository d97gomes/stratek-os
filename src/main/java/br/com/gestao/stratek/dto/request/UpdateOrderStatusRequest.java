package br.com.gestao.stratek.dto.request;

import br.com.gestao.stratek.enums.OrderStatus;

public record UpdateOrderStatusRequest(
        OrderStatus status
) {}