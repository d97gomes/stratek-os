package br.com.gestao.stratek.service;

import br.com.gestao.stratek.dto.request.ProductRequest;
import br.com.gestao.stratek.dto.response.ProductResponse;
import br.com.gestao.stratek.entity.Product;
import br.com.gestao.stratek.exception.ResourceNotFoundException;
import br.com.gestao.stratek.mapper.ProductMapper;
import br.com.gestao.stratek.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    // ✅ CREATE
    public ProductResponse create(ProductRequest request) {
        Product product = mapper.toEntity(request);
        product = repository.save(product);
        return mapper.toResponse(product);
    }

    // ✅ LIST (com paginação 🔥)
    public Page<ProductResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    // ✅ FIND BY ID
    public ProductResponse findById(UUID id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        return mapper.toResponse(product);
    }

    // ✅ UPDATE
    public ProductResponse update(UUID id, ProductRequest request) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        mapper.updateEntityFromDto(request, product);

        product = repository.save(product);

        return mapper.toResponse(product);
    }

    // ✅ DELETE
    public void delete(UUID id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        repository.deleteById(id);
    }
}