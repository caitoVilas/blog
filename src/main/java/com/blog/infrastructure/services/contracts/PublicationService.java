package com.blog.infrastructure.services.contracts;

import com.blog.api.models.requests.PublicationRequest;
import com.blog.api.models.responses.PublicationResponse;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author claudio.vilas
 * date. 09/2023
 */

public interface PublicationService {
    PublicationResponse save(PublicationRequest request);
    PublicationResponse getById(Long id);
    Page<PublicationResponse> getAll(int page, int size);
    PublicationResponse update(Long id, PublicationRequest request);
    void delete(Long id);
    List<PublicationResponse> getByTitle(String tile);
}
