package com.blog.util.maps;

import com.blog.api.models.requests.PublicationRequest;
import com.blog.api.models.responses.PublicationResponse;
import com.blog.domain.entites.Publication;

import java.util.stream.Collectors;

public class PublicationMap {

    public static Publication mapToEntity(PublicationRequest request){
        return Publication.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .content(request.getContent())
                .build();
    }

    public static PublicationResponse mapToDto(Publication publication){
        return PublicationResponse.builder()
                .id(publication.getId())
                .title(publication.getTitle())
                .description(publication.getDescription())
                .content(publication.getContent())
                .coments(publication.getComents().stream().map(ComentMap::mapToDto)
                        .collect(Collectors.toSet()))
                .build();
    }
}
