package com.blog.infrastructure.services.impl;

import com.blog.api.exceptions.customs.BadRequestException;
import com.blog.api.exceptions.customs.NotFoundException;
import com.blog.api.models.requests.ComentRequest;
import com.blog.api.models.responses.ComentResponse;
import com.blog.domain.repositories.ComentRepository;
import com.blog.domain.repositories.PublicationRepository;
import com.blog.infrastructure.services.contracts.ComentService;
import com.blog.util.constants.ComentConst;
import com.blog.util.constants.PublicationConst;
import com.blog.util.maps.ComentMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author claudio.vilas
 * date: 09/2023
 */

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ComentServiceImpl implements ComentService {
    private final ComentRepository comentRepository;
    private final PublicationRepository publicationRepository;

    @Override
    public ComentResponse create(ComentRequest request) {
        log.info("---> inicio servicio crear comentarios");
        log.info("---> validando entradas...");
        var publication = publicationRepository.findById(request.getPublication())
                .orElseThrow(() -> {
                    log.error("ERROR: " + PublicationConst.P_ID_NOT_FOUND + request.getPublication());
                    return new NotFoundException(PublicationConst.P_ID_NOT_FOUND + request.getPublication());
                });
        this.validateComent(request);
        log.info("---> guardando comentario...");
        var coment = ComentMap.mapToEntity(request);
        coment.setPublication(publication);
        var comentNew = comentRepository.save(coment);
        log.info("---> finalizado servicio crear comentarios");
        return ComentMap.mapToDto(comentNew);
    }

    @Override
    public ComentResponse getById(Long id) {
        log.info("--> inicio servicio buscar comentario por id");
        log.info("---> buscando comentario id {}...", id);
        var coment = comentRepository.findById(id).orElseThrow(()-> {
            log.error(ComentConst.C_ID_NOT_FOUND + id);
            return new NotFoundException(ComentConst.C_ID_NOT_FOUND + id);
        });
        log.info("---> finalizado servicio buscar comentario por id");
        return ComentMap.mapToDto(coment);
    }

    @Override
    public ComentResponse update(Long id, ComentRequest request) {
        log.info("---> inicio servicio actualizar comentario");
        log.info("---> buscando comentario id {}...", id);
        var coment = comentRepository.findById(id).orElseThrow(()-> {
            log.error(ComentConst.C_ID_NOT_FOUND + id);
            return new NotFoundException(ComentConst.C_ID_NOT_FOUND + id);
        });
        var publication = publicationRepository.findById(request.getPublication())
                .orElseThrow(()->{
                    log.error("ERROR: " + PublicationConst.P_ID_NOT_FOUND + request.getPublication());
                    return new NotFoundException(PublicationConst.P_ID_NOT_FOUND + request.getPublication());
                });
        if (!request.getName().isBlank())
            coment.setName(request.getName());
        if (!request.getEmail().isBlank())
            coment.setEmail(request.getEmail());
        if (!request.getBody().isBlank())
            coment.setBody(request.getBody());
        log.info("---> guardando actualizacion...");
        var comentNew = comentRepository.save(coment);
        log.info("---> finalizado servicio actualizar comentario");
        return ComentMap.mapToDto(comentNew);
    }

    @Override
    public void delete(Long id) {
        log.info("---> inicio servicio eliminar comentario por id");
        log.info("---> buscando comentario con id {}...", id);
        comentRepository.findById(id).orElseThrow(()-> {
            log.error(ComentConst.C_ID_NOT_FOUND + id);
            return new NotFoundException(ComentConst.C_ID_NOT_FOUND + id);
        });
        comentRepository.deleteById(id);
        log.info("---> finalizado servicio eliminar comentario por id");
    }

    private void validateComent(ComentRequest request){
        if (request.getName().isBlank()){
            log.error(ComentConst.C_NAME_EMPTY);
            throw new BadRequestException(ComentConst.C_NAME_EMPTY);
        }
        if (request.getEmail().isBlank()){
            log.error(ComentConst.C_EMAIL_EMPTY);
            throw new BadRequestException(ComentConst.C_EMAIL_EMPTY);
        }
        if (request.getBody().isBlank()){
            log.error(ComentConst.C_BODY_EMPTY);
            throw new BadRequestException(ComentConst.C_BODY_EMPTY);
        }
    }
}
