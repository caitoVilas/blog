package com.blog.domain.repositories;

import com.blog.domain.entites.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author claudio.vilas
 * date: 09/2023
 */

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
