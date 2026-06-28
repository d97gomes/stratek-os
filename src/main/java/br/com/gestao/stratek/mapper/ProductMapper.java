package br.com.gestao.stratek.mapper;

import br.com.gestao.stratek.dto.request.ProductRequest;
import br.com.gestao.stratek.dto.response.ProductResponse;
import br.com.gestao.stratek.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // DTO → Entity
    Product toEntity(ProductRequest request);

    // Entity → DTO
    ProductResponse toResponse(Product product);

    // Atualização parcial (IMPORTANTE 🔥)
    void updateEntityFromDto(ProductRequest request, @MappingTarget Product product);
}