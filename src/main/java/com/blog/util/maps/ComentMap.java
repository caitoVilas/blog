package com.blog.util.maps;

import com.blog.api.models.requests.ComentRequest;
import com.blog.api.models.responses.ComentResponse;
import com.blog.domain.entites.Coment;

/**
 * @author claudio.vilas
 * date: 09/2023
 */

public class ComentMap {
    public static Coment mapToEntity(ComentRequest coment){
        return Coment.builder()
                .name(coment.getName())
                .email(coment.getEmail())
                .body(coment.getBody())
                .build();
    }

    public static ComentResponse mapToDto(Coment coment){
        return ComentResponse.builder()
                .id(coment.getId())
                .name(coment.getName())
                .email(coment.getEmail())
                .body(coment.getBody())
                .build();
    }
}
