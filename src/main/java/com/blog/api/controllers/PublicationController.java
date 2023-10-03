package com.blog.api.controllers;

import com.blog.api.models.requests.PublicationRequest;
import com.blog.api.models.responses.PublicationResponse;
import com.blog.infrastructure.services.contracts.PublicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author claudio.vilas
 * date: 09/2023
 */

@RestController
@RequestMapping("/api/v1/blog/publications")
@Slf4j
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;

    @PostMapping
    public ResponseEntity<PublicationResponse> save(@RequestBody PublicationRequest request){
        log.info("#### endpoint guardar publicaciones ####");
        return ResponseEntity.ok(publicationService.save(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationResponse> getById(@PathVariable Long id){
        log.info("#### endpoint buscar publicacion por id ####");
        return ResponseEntity.ok(publicationService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<PublicationResponse>> getAll(){
        log.info("#### endpoint buscar publicaciones ####");
        var response = publicationService.getAll();
        if (response.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationResponse> update(@PathVariable Long id,
                                                      @RequestBody PublicationRequest request){
        log.info("#### endpoint actualizar publicaciones ####");
        return ResponseEntity.ok(publicationService.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        log.info("#### endpoint eliminar publicacion");
        publicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
