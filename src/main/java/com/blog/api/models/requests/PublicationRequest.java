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
public class PublicationRequest {
    private String title;
    private String description;
    private String content;
}
