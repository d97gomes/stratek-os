package br.com.gestao.stratek.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderServiceItemRequest(

        UUID serviceId,
        BigDecimal price

) {}