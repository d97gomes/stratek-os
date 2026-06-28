package br.com.gestao.stratek.mapper;

import br.com.gestao.stratek.dto.request.ServiceRequest;
import br.com.gestao.stratek.dto.response.ServiceResponse;
import br.com.gestao.stratek.entity.ServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceEntity toEntity(ServiceRequest request);

    ServiceResponse toResponse(ServiceEntity entity);

    void updateEntityFromDto(ServiceRequest request,
                             @MappingTarget ServiceEntity entity);
}