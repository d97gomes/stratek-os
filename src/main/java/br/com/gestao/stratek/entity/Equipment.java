package br.com.gestao.stratek.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "equipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderService order;

    private String name;
    private String brand;
    private String model;
    private String serialNumber;

    @Column(columnDefinition = "TEXT")
    private String conditions;

    @Column(columnDefinition = "TEXT")
    private String defects;

    @Column(columnDefinition = "TEXT")
    private String accessories;

    @Column(columnDefinition = "TEXT")
    private String solution;
}