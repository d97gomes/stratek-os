package br.com.gestao.stratek.mapper;

import br.com.gestao.stratek.dto.response.*;
import br.com.gestao.stratek.entity.OrderService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderServiceMapper {

    OrderServiceResponse toResponse(OrderService order);

}