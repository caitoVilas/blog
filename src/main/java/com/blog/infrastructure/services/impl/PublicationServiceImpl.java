package com.blog.infrastructure.services.impl;

import com.blog.api.exceptions.customs.BadRequestException;
import com.blog.api.exceptions.customs.NotFoundException;
import com.blog.api.models.requests.PublicationRequest;
import com.blog.api.models.responses.PublicationResponse;
import com.blog.domain.repositories.PublicationRepository;
import com.blog.infrastructure.services.contracts.PublicationService;
import com.blog.util.constants.PublicationConst;
import com.blog.util.maps.PublicationMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author claudio.vilas
 * date. 09/2023
 */

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    private final PublicationRepository publicationRepository;

    @Override
    public PublicationResponse save(PublicationRequest request) {
        log.info("---> inicio servicio guardar publicacion");
        log.info("---> validando entradas...");
        this.validatePublication(request);
        log.info("---> guardando publicacion...");
        var publication = publicationRepository.save(PublicationMap.mapToEntity(request));
        log.info("---> finalizado servicio guardar publicacion");
        return PublicationMap.mapToDto(publication);
    }

    @Override
    public PublicationResponse getById(Long id) {
        log.info("---> inicio servicio buscar publicacion por id");
        log.info("---> buscando publicacion con id: {}...", id);
        var publication = publicationRepository.findById(id).orElseThrow(()-> {
                    log.error(PublicationConst.P_ID_NOT_FOUND + id);
                    return new  NotFoundException(PublicationConst.P_ID_NOT_FOUND + id);
                });
        log.info("---> finalizado servicio buscar publicacion por id");
        return PublicationMap.mapToDto(publication);
    }
    @Override
    public Page<PublicationResponse> getAll(int page, int size) {
        log.info("---> inicio servicio buscar publicaciones");
        log.info("---> buscando publicaciones...");
        PageRequest pr = PageRequest.of(page, size);
        log.info("---> finalizado servicio buscar publicaciones");
        var publicaciones = publicationRepository.findAll(pr);

        return publicationRepository.findAll(pr).map(PublicationMap::mapToDto);
    }

    @Override
    public PublicationResponse update(Long id, PublicationRequest request) {
        log.info("---> inicio servicio actualizar publicaciones");
        log.info("---> buscando publicacion con id: {}...", id);
        var publication = publicationRepository.findById(id).orElseThrow(()-> {
            log.error(PublicationConst.P_ID_NOT_FOUND + id);
            return new  NotFoundException(PublicationConst.P_ID_NOT_FOUND + id);
        });
        if (!request.getTitle().isBlank())
            publication.setTitle(request.getTitle());
        if (!request.getDescription().isBlank())
            publication.setDescription(request.getDescription());
        if (!request.getContent().isBlank())
            publication.setContent(request.getContent());
        log.info("---> guardando actualizacion...");
        publicationRepository.save(publication);
        log.info("---> finalizado servicio actualizar publicaciones");
        return PublicationMap.mapToDto(publication);
    }

    @Override
    public void delete(Long id) {
        log.info("---> inicio servicio eliminar publicacion");
        log.info("---> buscando publicacion con id: {}...", id);
        publicationRepository.findById(id).orElseThrow(()-> {
            log.error(PublicationConst.P_ID_NOT_FOUND + id);
            return new  NotFoundException(PublicationConst.P_ID_NOT_FOUND + id);
        });
        log.info("---> eliminando publicacion...");
        publicationRepository.deleteById(id);
        log.info("---> finalizado servicio eliminar publicacion");
    }

    @Override
    public List<PublicationResponse> getByTitle(String tile) {
        log.info("---> inicio servicio buscar publicacion por titulo");
        log.info("---> buscando publicaciones con titulo {}...", tile);
        var response = publicationRepository.findByTitleContaining(tile);
        log.info("---> finalizado servicio buscar publicacion por titulo");
        return response.stream().map(PublicationMap::mapToDto).collect(Collectors.toList());
    }

    private void validatePublication(PublicationRequest request){
        if (request.getTitle().isBlank()){
            log.error("ERROR: " + PublicationConst.P_NO_TITLE);
            throw new BadRequestException(PublicationConst.P_NO_TITLE);
        }
        if (request.getDescription().isBlank()){
            log.error("ERROR: " + PublicationConst.P_NO_DESCRIPTION);
            throw new BadRequestException(PublicationConst.P_NO_DESCRIPTION);
        }
        if (request.getContent().isBlank()){
            log.error("ERROR: " + PublicationConst.P_NO_CONTENT);
            throw new BadRequestException(PublicationConst.P_NO_CONTENT);
        }
    }
}
