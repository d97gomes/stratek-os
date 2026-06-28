package br.com.gestao.stratek.repository;

import br.com.gestao.stratek.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}