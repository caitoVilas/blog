package com.blog.api.controllers;

import com.blog.api.models.requests.ComentRequest;
import com.blog.api.models.responses.ComentResponse;
import com.blog.infrastructure.services.contracts.ComentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author claudio.vilas
 * date: 09/2023
 */

@RestController
@RequestMapping("/api/v1/blog/coments")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Blog - Comentarios")
public class ComentController {
    private final ComentService comentService;

    @PostMapping
    @Operation(summary = "servicio para guardar comentarios en el blog",
            description = "servicio guardar comentarios en el blog")
    @Parameter(name = "request", description = "datos del comentario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<ComentResponse> create(@RequestBody ComentRequest request){
        log.info("#### endpoint creacion de comentarios ####");
        return ResponseEntity.ok(comentService.create(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "servicio para obtener comentarios por id",
            description = "servicio para obtener comentarios por id si existe")
    @Parameter(name = "id", description = "id del comentario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<ComentResponse> finById(@PathVariable Long id){
        log.info("#### endpoinp buscar comentario por id ####");
        return ResponseEntity.ok(comentService.getById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "servicio para actualizar comentarios",
            description = "servicio para actualizar comentarios")
    @Parameters({
            @Parameter(name = "id", description = "id del comentario"),
            @Parameter(name = "request", description = "datos del comentario")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<ComentResponse> update(@PathVariable Long id,
                                                 @RequestBody ComentRequest request){
        log.info("#### endpoint actualizacion de comentario ####");
        return ResponseEntity.ok(comentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "servicio para eliminar comentarios por id",
            description = "servicio para eliminar comentarios por id si existe")
    @Parameter(name = "id", description = "id del comentario")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        log.info("#### endpoint eliminar comentarios por id");
        comentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
