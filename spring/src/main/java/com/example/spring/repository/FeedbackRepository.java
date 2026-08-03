package com.example.spring.repository;

import com.example.spring.entity.Feedback;
import com.example.spring.entity.FeedbackStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("""
            select f
            from Feedback f
            join fetch f.movie
            where f.user.id = :userId
            and f.status = :status
            order by f.id desc
            """)
    List<Feedback> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") FeedbackStatus status);

    Optional<Feedback> findByUserIdAndMovieId(Long userId, Long movieId);
}
