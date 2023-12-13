package com.poppin.reviewservice.service;

import com.poppin.reviewservice.domain.Review;
import com.poppin.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void createReview(Long authorId, Long locationId, int rating, String title, String text) {
        reviewRepository.save(new Review(authorId, locationId, rating, title, text));
    }

    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException("Review with id " + reviewId + " not found"));
    }

    public Page<Review> getReviewsByUserId(Long userId, Pageable pageable) {
        return reviewRepository.findAllByUserId(userId, pageable);
    }

    public Page<Review> getReviewsByLocationId(Long locationId, Pageable pageable) {
        return reviewRepository.findAllByLocationId(locationId, pageable);
    }

    public void updateReview(Long reviewId, int rating, String title, String text) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException("Review with id " + reviewId + " not found"));
        review.updateReview(rating, title, text);
        reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
