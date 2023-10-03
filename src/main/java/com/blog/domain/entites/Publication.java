package com.blog.domain.entites;

import lombok.*;

import javax.persistence.*;

/**
 * @author claudio.vilas
 * date: 09/2023
 */

@Entity
@Table(name = "publications")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 70)
    private String title;
    @Column(length = 100)
    private String description;
    private String content;
}
