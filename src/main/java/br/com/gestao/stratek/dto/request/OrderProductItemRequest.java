package br.com.gestao.stratek.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderProductItemRequest(

        UUID productId,
        Integer quantity,
        BigDecimal price

) {}
