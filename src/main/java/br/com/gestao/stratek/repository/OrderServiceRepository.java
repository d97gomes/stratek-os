package br.com.gestao.stratek.repository;

import br.com.gestao.stratek.entity.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderServiceRepository extends JpaRepository<OrderService, UUID> {
}