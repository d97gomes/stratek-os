package br.com.gestao.stratek.service;

import br.com.gestao.stratek.dto.request.AddressRequest;
import br.com.gestao.stratek.dto.response.AddressResponse;
import br.com.gestao.stratek.entity.Address;
import br.com.gestao.stratek.entity.Customer;
import br.com.gestao.stratek.exception.ResourceNotFoundException;
import br.com.gestao.stratek.mapper.AddressMapper;
import br.com.gestao.stratek.repository.AddressRepository;
import br.com.gestao.stratek.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;
    private final CustomerRepository customerRepository;
    private final AddressMapper mapper;

    // ✅ CREATE
    public AddressResponse create(AddressRequest request) {

        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        Address address = mapper.toEntity(request);

        address.setCustomer(customer);

        address = repository.save(address);

        return mapper.toResponse(address);
    }

    // ✅ LIST
    public Page<AddressResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    // ✅ FIND BY ID
    public AddressResponse findById(UUID id) {
        Address address = findAddressById(id);
        return mapper.toResponse(address);
    }

    // ✅ UPDATE
    public AddressResponse update(UUID id, AddressRequest request) {

        Address address = findAddressById(id);

        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        mapper.updateEntityFromDto(request, address);

        address.setCustomer(customer);

        address = repository.save(address);

        return mapper.toResponse(address);
    }

    // ✅ DELETE
    public void delete(UUID id) {
        Address address = findAddressById(id);
        repository.delete(address);
    }

    private Address findAddressById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }
}