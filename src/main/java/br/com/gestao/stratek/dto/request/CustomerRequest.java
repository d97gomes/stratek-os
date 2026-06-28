package br.com.gestao.stratek.dto.request;

import br.com.gestao.stratek.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
        String name,

        @NotBlank(message = "Documento é obrigatório")
        String document,

        @Email(message = "Email inválido")
        String email,

        @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
        String phone,

        @NotBlank(message = "Genero é obrigatorio")
        Gender gender

) {
}