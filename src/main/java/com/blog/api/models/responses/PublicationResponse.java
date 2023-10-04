package com.blog.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author claudio.vilas
 * date: 09/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PublicationResponse {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<ComentResponse> coments;
}
