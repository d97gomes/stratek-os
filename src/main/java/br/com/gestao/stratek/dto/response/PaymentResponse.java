package br.com.gestao.stratek.dto.response;

import br.com.gestao.stratek.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(

        PaymentMethod method,
        BigDecimal amount,
        LocalDateTime paymentDate

) {}
