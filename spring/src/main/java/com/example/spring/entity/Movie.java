package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_ko", nullable = false, length = 100)
    private String titleKo;

    @Column(name = "title_eng", nullable = false, length = 100)
    private String titleEng;

    @Column(nullable = false)
    private Integer runtime;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false, length = 50)
    private String nation;

    @Lob
    @Column(name = "poster_url")
    private String posterUrl;

    @Column(nullable = false, length = 50)
    private String director;

    @Column(nullable = false, length = 100)
    private String actors;

    @Lob
    @Column(nullable = false)
    private String synopsis;

    @Column(name = "synopsis_vector", nullable = false, columnDefinition = "vector(1536)")
    private String synopsisVector;

    @Builder.Default
    @OneToMany(mappedBy = "movie")
    private List<Feedback> feedbacks = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "movie")
    private List<MovieGenre> movieGenres = new ArrayList<>();
}