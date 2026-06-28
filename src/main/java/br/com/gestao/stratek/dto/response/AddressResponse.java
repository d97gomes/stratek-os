package br.com.gestao.stratek.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record AddressResponse(

        UUID id,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipCode,
        String country,
        Boolean isDefault,
        UUID customerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}