package com.example.spring.repository;

import com.example.spring.entity.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTmdbId(Long id);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE movies
        SET synopsis_vector = CAST(:vector AS vector)
        WHERE id = :id
        """, nativeQuery = true)
    void updateEmbedding(
            @Param("id") Long id,
            @Param("vector") String vector
    );
}
