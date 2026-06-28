package br.com.gestao.stratek.dto.request;

public record EquipmentRequest(

        String name,
        String brand,
        String model,
        String serialNumber,
        String conditions,
        String defects,
        String accessories,
        String solution

) {}