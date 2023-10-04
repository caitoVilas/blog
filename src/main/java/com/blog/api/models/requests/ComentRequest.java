package com.blog.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author claudio.vilas
 * date: 09/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ComentRequest {
    private String name;
    private String email;
    private String body;
    private Long publication;
}
