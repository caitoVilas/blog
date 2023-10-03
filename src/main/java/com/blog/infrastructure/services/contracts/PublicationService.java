package com.blog.infrastructure.services.contracts;

import com.blog.api.models.requests.PublicationRequest;
import com.blog.api.models.responses.PublicationResponse;

import java.util.List;

/**
 * @author claudio.vilas
 * date. 09/2023
 */

public interface PublicationService {
    PublicationResponse save(PublicationRequest request);
    PublicationResponse getById(Long id);
    List<PublicationResponse> getAll();
    PublicationResponse update(Long id, PublicationRequest request);
    void delete(Long id);
}
