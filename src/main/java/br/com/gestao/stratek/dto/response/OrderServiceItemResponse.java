package br.com.gestao.stratek.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderServiceItemResponse(

        UUID serviceId,
        String serviceName,
        BigDecimal price

) {}