package br.com.gestao.stratek.mapper;

import br.com.gestao.stratek.dto.response.*;
import br.com.gestao.stratek.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderServiceMapper {

    // ✅ MAPSTRUCT (campos simples)
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerName", source = "customer.name")
    @Mapping(target = "equipments", source = "equipments")
    @Mapping(target = "serviceItems", source = "serviceItems")
    @Mapping(target = "productItems", source = "productItems")
    @Mapping(target = "payment", source = "payment")
    OrderServiceResponse toResponse(OrderService order);


    // ✅ MAPEAMENTO MANUAL (default methods)

    default List<EquipmentResponse> mapEquipments(List<Equipment> equipments) {
        if (equipments == null) return null;

        return equipments.stream()
                .map(eq -> new EquipmentResponse(
                        eq.getId(),
                        eq.getName(),
                        eq.getBrand(),
                        eq.getModel(),
                        eq.getSerialNumber(),
                        eq.getConditions(),
                        eq.getDefects(),
                        eq.getAccessories(),
                        eq.getSolution()
                ))
                .toList();
    }

    default List<OrderServiceItemResponse> mapServiceItems(List<OrderServiceItem> items) {
        if (items == null) return null;

        return items.stream()
                .map(item -> new OrderServiceItemResponse(
                        item.getService() != null ? item.getService().getId() : null,
                        item.getService() != null ? item.getService().getName() : null,
                        item.getPrice()
                ))
                .toList();
    }

    default List<OrderProductItemResponse> mapProductItems(List<OrderProductItem> items) {
        if (items == null) return null;

        return items.stream()
                .map(item -> new OrderProductItemResponse(
                        item.getProduct() != null ? item.getProduct().getId() : null,
                        item.getProduct() != null ? item.getProduct().getName() : null,
                        item.getQuantity(),
                        item.getPrice()
                ))
                .toList();
    }

    default PaymentResponse mapPayment(Payment payment) {
        if (payment == null) return null;

        return new PaymentResponse(
                payment.getMethod(),
                payment.getAmount(),
                payment.getPaymentDate()
        );
    }
}