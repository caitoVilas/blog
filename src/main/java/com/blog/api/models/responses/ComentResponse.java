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
public class ComentResponse {
    private Long id;
    private String name;
    private String email;
    private String body;
}
