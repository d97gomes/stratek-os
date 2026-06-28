package br.com.gestao.stratek.entity;

import br.com.gestao.stratek.enums.OrderStatus;
import br.com.gestao.stratek.enums.PaymentStatus;
import br.com.gestao.stratek.enums.Priority;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderService {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // ✅ Cliente
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // ✅ Número da OS (legível)
    private String number;

    // ✅ Status operacional
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // ✅ Status financeiro
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    // ✅ Datas
    private LocalDateTime entryDateTime;
    private LocalDateTime exitDateTime;

    // ✅ Informações adicionais
    private String channel;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    // ✅ Totais (calculados no backend)
    private BigDecimal totalProducts;
    private BigDecimal totalServices;
    private BigDecimal total;

    // ✅ Payment (embedded)
    @Embedded
    private Payment payment;

    // ✅ Equipamentos (dependente da OS)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Equipment> equipments;

    // ✅ Serviços (itens)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderServiceItem> serviceItems;

    // ✅ Produtos (itens)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProductItem> productItems;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.CREATED;
        this.paymentStatus = PaymentStatus.PENDING;
    }
}