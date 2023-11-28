package com.poppin.reviewservice.repository;

import com.poppin.reviewservice.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByLocationId(Long locationId, Pageable pageable);
    Page<Review> findAllByUserId(Long userId, Pageable pageable);
}
