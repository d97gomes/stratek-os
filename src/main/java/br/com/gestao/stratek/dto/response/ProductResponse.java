package br.com.gestao.stratek.dto.response;

import br.com.gestao.stratek.enums.UnitType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponse(

        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        UnitType unitType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}