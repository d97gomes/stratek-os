package br.com.gestao.stratek.mapper;

import br.com.gestao.stratek.dto.request.CustomerRequest;
import br.com.gestao.stratek.dto.response.CustomerResponse;
import br.com.gestao.stratek.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // ✅ DTO → Entity
    Customer toEntity(CustomerRequest request);

    // ✅ Entity → DTO
    CustomerResponse toResponse(Customer customer);

    // ✅ UPDATE (🔥 IMPORTANTE)
    void updateEntityFromDto(CustomerRequest request, @MappingTarget Customer customer);

}