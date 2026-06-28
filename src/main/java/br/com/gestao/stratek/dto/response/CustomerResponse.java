package br.com.gestao.stratek.dto.response;

import br.com.gestao.stratek.enums.Gender;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponse(

        UUID id,
        String name,
        String document,
        String email,
        String phone,
        Gender gender,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
