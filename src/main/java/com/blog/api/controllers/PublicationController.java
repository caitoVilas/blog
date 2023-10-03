package com.blog.api.controllers;

import com.blog.api.models.requests.PublicationRequest;
import com.blog.api.models.responses.PublicationResponse;
import com.blog.infrastructure.services.contracts.PublicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
@Tag(name = "Blog - Publicaciones")
public class PublicationController {
    private final PublicationService publicationService;

    @PostMapping
    @Operation(summary = "servicio para guardar publicaciones en el blog",
              description = "servicio guardar publicaciones en el blog")
    @Parameter(name = "request", description = "datos de la publicacion")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<PublicationResponse> save(@RequestBody PublicationRequest request){
        log.info("#### endpoint guardar publicaciones ####");
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationService.save(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "servicio para obtener publicaciones por id",
            description = "servicio para obtener publicaciones por id si existe")
    @Parameter(name = "id", description = "id de la publicacion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<PublicationResponse> getById(@PathVariable Long id){
        log.info("#### endpoint buscar publicacion por id ####");
        return ResponseEntity.ok(publicationService.getById(id));
    }

    @GetMapping
    @Operation(summary = "servicio para obtener publicaciones",
            description = "servicio para obtener publicaciones")
    @Parameters({
            @Parameter(name = "page", description = "numero pagina 0->"),
            @Parameter(name = "size", description = "numero de elementos")
    }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<Page<PublicationResponse>> getAll(@RequestParam int page,
                                                            @RequestParam int size){
        log.info("#### endpoint buscar publicaciones ####");
        if (page < 0) page = 0;
        if (size < 0) size = 10;
        var response = publicationService.getAll(page, size);
        if (response.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "servicio para actualizar publicaciones",
            description = "servicio para actualizar publicaciones")
    @Parameters({
            @Parameter(name = "id", description = "id de la publicacion"),
            @Parameter(name = "request", description = "datos de la publicacion")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<PublicationResponse> update(@PathVariable Long id,
                                                      @RequestBody PublicationRequest request){
        log.info("#### endpoint actualizar publicaciones ####");
        return ResponseEntity.ok(publicationService.update(id,request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "servicio para eliminar publicaciones por id",
            description = "servicio para eliminar publicaciones por id si existe")
    @Parameter(name = "id", description = "id de la publicacion")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        log.info("#### endpoint eliminar publicacion");
        publicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/title/{title}")
    @Operation(summary = "servicio para obtener publicaciones por titulo",
            description = "servicio para obtener publicaciones por titulo si existe")
    @Parameter(name = "title", description = "titulo de la publicacion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<List<PublicationResponse>> getByTitle(@PathVariable String title){
                log.info("#### endpoint buscar publicaciones por titulo ####");
                var response = publicationService.getByTitle(title);
                if (response.isEmpty())
                    return ResponseEntity.noContent().build();
                return ResponseEntity.ok(response);
    }
}
