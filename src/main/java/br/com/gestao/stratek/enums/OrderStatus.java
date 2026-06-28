package br.com.gestao.stratek.enums;

public enum OrderStatus {

    CREATED,          // OS criada
    IN_PROGRESS,      // em execução
    WAITING_PARTS,    // aguardando peça
    COMPLETED,        // serviço finalizado
    DELIVERED,        // entregue ao cliente
    CANCELED          // cancelada

}