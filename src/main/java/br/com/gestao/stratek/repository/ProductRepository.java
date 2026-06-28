package br.com.gestao.stratek.repository;

import br.com.gestao.stratek.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}