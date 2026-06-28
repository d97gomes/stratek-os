package br.com.gestao.stratek.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddressRequest(

        @NotBlank(message = "Rua é obrigatória")
        String street,

        @NotBlank(message = "Número é obrigatório")
        String number,

        String complement,

        @NotBlank(message = "Bairro é obrigatório")
        String neighborhood,

        @NotBlank(message = "Cidade é obrigatória")
        String city,

        @NotBlank(message = "Estado é obrigatório")
        String state,

        @NotBlank(message = "CEP é obrigatório")
        String zipCode,

        @NotBlank(message = "País é obrigatório")
        String country,

        Boolean isDefault,

        @NotNull(message = "CustomerId é obrigatório")
        UUID customerId

) {
}