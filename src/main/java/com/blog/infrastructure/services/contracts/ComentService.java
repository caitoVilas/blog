package com.blog.infrastructure.services.contracts;

import com.blog.api.models.requests.ComentRequest;
import com.blog.api.models.responses.ComentResponse;

/**
 * @author claudio.vilas
 * date: 09/2023
 */

public interface ComentService {
    ComentResponse create(ComentRequest request);
    ComentResponse getById(Long id);
    ComentResponse update(Long id, ComentRequest request);
    void delete(Long id);
}
