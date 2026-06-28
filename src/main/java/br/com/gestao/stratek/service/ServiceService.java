package br.com.gestao.stratek.service;

import br.com.gestao.stratek.dto.request.ServiceRequest;
import br.com.gestao.stratek.dto.response.ServiceResponse;
import br.com.gestao.stratek.entity.ServiceEntity;
import br.com.gestao.stratek.exception.ResourceNotFoundException;
import br.com.gestao.stratek.mapper.ServiceMapper;
import br.com.gestao.stratek.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository repository;
    private final ServiceMapper mapper;

    // ✅ CREATE
    public ServiceResponse create(ServiceRequest request) {
        ServiceEntity entity = mapper.toEntity(request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    // ✅ LIST
    public Page<ServiceResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    // ✅ FIND BY ID
    public ServiceResponse findById(UUID id) {
        ServiceEntity entity = findEntityById(id);
        return mapper.toResponse(entity);
    }

    // ✅ UPDATE
    public ServiceResponse update(UUID id, ServiceRequest request) {
        ServiceEntity entity = findEntityById(id);
        mapper.updateEntityFromDto(request, entity);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    // ✅ DELETE
    public void delete(UUID id) {
        ServiceEntity entity = findEntityById(id);
        repository.delete(entity);
    }

    // ✅ MÉTODO INTERNO (PADRÃO)
    private ServiceEntity findEntityById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Serviço não encontrado"));
    }
}