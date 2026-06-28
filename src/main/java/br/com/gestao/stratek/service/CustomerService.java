package br.com.gestao.stratek.service;

import br.com.gestao.stratek.dto.request.CustomerRequest;
import br.com.gestao.stratek.dto.response.CustomerResponse;
import br.com.gestao.stratek.entity.Customer;
import br.com.gestao.stratek.exception.ResourceNotFoundException;
import br.com.gestao.stratek.mapper.CustomerMapper;
import br.com.gestao.stratek.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    // ✅ CREATE
    public CustomerResponse create(CustomerRequest request) {

        Customer customer = mapper.toEntity(request);
        customer = repository.save(customer);

        return mapper.toResponse(customer);
    }

    // ✅ LIST (com paginação)
    public Page<CustomerResponse> findAll(Pageable pageable) {

        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    // ✅ FIND BY ID
    public CustomerResponse findById(UUID id) {

        Customer customer = findCustomerById(id);

        return mapper.toResponse(customer);
    }

    // ✅ UPDATE
    public CustomerResponse update(UUID id, CustomerRequest request) {

        Customer customer = findCustomerById(id);

        mapper.updateEntityFromDto(request, customer);

        customer = repository.save(customer);

        return mapper.toResponse(customer);
    }

    // ✅ DELETE
    public void delete(UUID id) {

        Customer customer = findCustomerById(id);

        repository.delete(customer);
    }

    // ✅ MÉTODO PRIVADO (🔥 PADRÃO PROFISSIONAL)
    private Customer findCustomerById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }
}