package br.com.gestao.stratek.dto.request;

import br.com.gestao.stratek.enums.Priority;

import java.time.LocalDateTime;

public record UpdateOrderServiceRequest(

        Priority priority,
        String channel,
        LocalDateTime exitDateTime

) {}