package br.com.gestao.stratek.dto.response;

import br.com.gestao.stratek.enums.WarrantyStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record WarrantyResponse(

        UUID id,
        UUID orderId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String description,
        String problem,
        WarrantyStatus status,
        LocalDateTime createdAt

) {}
