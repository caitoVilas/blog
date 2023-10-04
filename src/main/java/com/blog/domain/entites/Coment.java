package com.blog.domain.entites;

import lombok.*;

import javax.persistence.*;

/**
 * @author claudio.vilas
 * date: 09/2023
 */

@Entity
@Table(name = "coments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Coment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String body;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pubication_id", nullable = false)
    private Publication publication;
}
