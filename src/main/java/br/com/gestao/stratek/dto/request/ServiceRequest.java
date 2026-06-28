package br.com.gestao.stratek.dto.request;

import java.math.BigDecimal;

public record ServiceRequest(
        String name,
        String description,
        BigDecimal price
) {
}