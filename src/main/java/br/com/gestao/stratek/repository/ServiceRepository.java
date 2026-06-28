package br.com.gestao.stratek.repository;

import br.com.gestao.stratek.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<ServiceEntity, UUID> {
}