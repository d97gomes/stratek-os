package br.com.gestao.stratek.dto.request;


import br.com.gestao.stratek.enums.UnitType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
        String name,

        @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
        String description,

        @NotNull(message = "Preço é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
        BigDecimal price,

        @NotNull(message = "Estoque é obrigatório")
        @Min(value = 0, message = "Estoque não pode ser negativo")
        Integer stock,

        @NotNull(message = "Tipo de unidade é obrigatório")
        UnitType unitType

) {
}

