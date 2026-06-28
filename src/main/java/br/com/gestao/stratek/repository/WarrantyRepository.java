package br.com.gestao.stratek.repository;

import br.com.gestao.stratek.entity.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WarrantyRepository extends JpaRepository<Warranty, UUID> {

    Optional<Warranty> findByOrderServiceId(UUID orderServiceId);

}
