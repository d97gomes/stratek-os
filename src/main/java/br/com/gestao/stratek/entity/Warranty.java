package br.com.gestao.stratek.entity;

import br.com.gestao.stratek.enums.WarrantyStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "warranties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warranty {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // ✅ Relacionamento com OrderService
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private OrderService orderService;

    // ✅ Datas
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // ✅ Informações da garantia
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String problem;

    // ✅ Status
    @Enumerated(EnumType.STRING)
    private WarrantyStatus status;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        // ✅ status inicial
        this.status = WarrantyStatus.ACTIVE;
    }
}