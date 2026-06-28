package br.com.gestao.stratek.service;

import br.com.gestao.stratek.dto.request.*;
import br.com.gestao.stratek.dto.response.OrderServiceResponse;
import br.com.gestao.stratek.entity.*;
import br.com.gestao.stratek.enums.OrderStatus;
import br.com.gestao.stratek.exception.ResourceNotFoundException;
import br.com.gestao.stratek.mapper.OrderServiceMapper;
import br.com.gestao.stratek.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceService {

    private final OrderServiceRepository repository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ServiceRepository serviceRepository;
    private final OrderServiceMapper mapper;

    // CREATE
    public OrderServiceResponse create(OrderServiceRequest request) {

        // 🔹 1. Cliente
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        OrderService order = new OrderService();
        order.setCustomer(customer);
        order.setEntryDateTime(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);

        // FIX LAMBDA
        final OrderService currentOrder = order;

        // 🔹 2. Equipamentos
        if (request.equipments() != null) {

            List<Equipment> equipments = request.equipments()
                    .stream()
                    .map(eq -> Equipment.builder()
                            .name(eq.name())
                            .brand(eq.brand())
                            .model(eq.model())
                            .serialNumber(eq.serialNumber())
                            .conditions(eq.conditions())
                            .defects(eq.defects())
                            .accessories(eq.accessories())
                            .solution(eq.solution())
                            .order(currentOrder)
                    .build()
                    )
                    .collect(Collectors.toList());

            order.setEquipments(equipments);
        }

        // 🔹 3. Service Items
        BigDecimal totalServices = BigDecimal.ZERO;

        if (request.serviceItems() != null) {

            List<OrderServiceItem> serviceItems = request.serviceItems()
                    .stream()
                    .map(item -> {

                        ServiceEntity service = serviceRepository.findById(item.serviceId())
                                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));

                        return OrderServiceItem.builder()
                                .order(currentOrder)
                                .service(service)
                                .price(item.price())
                                .build();

                    }).collect(Collectors.toList());

            totalServices = serviceItems.stream()
                    .map(OrderServiceItem::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            order.setServiceItems(serviceItems);
        }

        // 🔹 4. Product Items
        BigDecimal totalProducts = BigDecimal.ZERO;

        if (request.productItems() != null) {

            List<OrderProductItem> productItems = request.productItems()
                    .stream()
                    .map(item -> {

                        Product product = productRepository.findById(item.productId())
                                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

                        return OrderProductItem.builder()
                                .order(currentOrder)
                                .product(product)
                                .quantity(item.quantity())
                                .price(item.price())
                                .build();

                    }).collect(Collectors.toList());

            totalProducts = productItems.stream()
                    .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            order.setProductItems(productItems);
        }

        //  5. Totais
        order.setTotalServices(totalServices);
        order.setTotalProducts(totalProducts);
        order.setTotal(totalServices.add(totalProducts));

        //  6. Payment
        if (request.payment() != null) {

            Payment payment = Payment.builder()
                    .method(request.payment().method())
                    .amount(request.payment().amount())
                    .paymentDate(LocalDateTime.now())
                    .build();

            order.setPayment(payment);
        }

        //  7. salvar
        order = repository.save(order);

        //  8. retorno
        return mapper.toResponse(order);
    }

    //  LIST
    public Page<OrderServiceResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    //  FIND BY ID
    public OrderServiceResponse findById(UUID id) {
        OrderService order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OS não encontrada"));

        return mapper.toResponse(order);
    }

    //  DELETE
    public void delete(UUID id) {
        OrderService order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OS não encontrada"));

        repository.delete(order);
    }

    // PUT STATUS
    public OrderServiceResponse updateStatus(UUID id, OrderStatus status) {
        OrderService order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OS não encontrada"));

        order.setStatus(status);
        order = repository.save(order);

        return mapper.toResponse(order);
    }

    // PUT PAYMENT
    public OrderServiceResponse updatePayment(UUID id, UpdatePaymentRequest request) {

        // 🔹 1. Buscar OS
        OrderService order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OS não encontrada"));

        // 🔹 2. Criar pagamento
        Payment payment = Payment.builder()
                .method(request.method())
                .amount(request.amount())
                .paymentDate(LocalDateTime.now())
                .build();

        // 🔹 3. Atualizar payment
        order.setPayment(payment);

        // 🔹 4. Atualizar status financeiro
        order.setPaymentStatus(
                request.amount() != null && order.getTotal() != null &&
                        request.amount().compareTo(order.getTotal()) >= 0
                        ? br.com.gestao.stratek.enums.PaymentStatus.PAID
                        : br.com.gestao.stratek.enums.PaymentStatus.PENDING
        );

        // 🔹 5. salvar
        order = repository.save(order);

        // 🔹 6. retornar
        return mapper.toResponse(order);
    }

    //Put dados complementares. Channel e priority.
    public OrderServiceResponse update(UUID id, UpdateOrderServiceRequest request) {

        // 🔹 1. Buscar OS
        OrderService order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OS não encontrada"));

        // 🔹 2. Atualizar campos básicos (somente se vierem no request)

        if (request.priority() != null) {
            order.setPriority(request.priority());
        }

        if (request.channel() != null) {
            order.setChannel(request.channel());
        }

        if (request.exitDateTime() != null) {
            order.setExitDateTime(request.exitDateTime());
        }

        // 🔹 3. salvar
        order = repository.save(order);

        return mapper.toResponse(order);
    }
}