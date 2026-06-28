package br.com.gestao.stratek.dto.request;

import br.com.gestao.stratek.enums.PaymentMethod;

import java.math.BigDecimal;

public record UpdatePaymentRequest(
        PaymentMethod method,
        BigDecimal amount
) {
}
