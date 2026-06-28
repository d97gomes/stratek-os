package br.com.gestao.stratek.entity;

import br.com.gestao.stratek.enums.PaymentMethod;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Payment {

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
}