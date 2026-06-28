package br.com.gestao.stratek.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderProductItemResponse(

        UUID productId,
        String productName,
        Integer quantity,
        BigDecimal price

) {}
