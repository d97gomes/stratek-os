package br.com.gestao.stratek.dto.response;

import java.util.UUID;

public record EquipmentResponse(

        UUID id,
        String name,
        String brand,
        String model,
        String serialNumber,
        String conditions,
        String defects,
        String accessories,
        String solution

) {}