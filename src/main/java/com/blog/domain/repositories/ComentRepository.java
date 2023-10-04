package com.blog.domain.repositories;

import com.blog.domain.entites.Coment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author claudio.vilas
 * date: 09/2023
 */
@Repository
public interface ComentRepository extends JpaRepository<Coment, Long> {
}
