package br.com.gestao.stratek.mapper;

import br.com.gestao.stratek.dto.request.AddressRequest;
import br.com.gestao.stratek.dto.response.AddressResponse;
import br.com.gestao.stratek.entity.Address;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "customer.id", source = "customerId")
    Address toEntity(AddressRequest request);

    @Mapping(target = "customerId", source = "customer.id")
    AddressResponse toResponse(Address address);

    void updateEntityFromDto(AddressRequest request, @MappingTarget Address address);
}