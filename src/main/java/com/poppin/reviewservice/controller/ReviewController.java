package com.poppin.reviewservice.controller;

import com.poppin.reviewservice.domain.Review;
import com.poppin.reviewservice.dto.request.CreateReviewRequestDto;
import com.poppin.reviewservice.dto.request.UpdateReviewRequestDto;
import com.poppin.reviewservice.dto.response.ReviewResponseDto;
import com.poppin.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/")
    public void createReview(CreateReviewRequestDto requestDto) {
        reviewService.createReview(requestDto.getUserId(), requestDto.getLocationId(), requestDto.getRating(), requestDto.getTitle(), requestDto.getText());
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> getReview(@PathVariable Long reviewId) {
        Review review = reviewService.getReview(reviewId);
        ReviewResponseDto result =  ReviewResponseDto.builder()
                .id(review.getId())
                .userId(review.getUserId())
                .locationId(review.getLocationId())
                .title(review.getTitle())
                .text(review.getText())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ReviewResponseDto>> getReviewsByUserId(@PathVariable Long userId, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Review> reviews = reviewService.getReviewsByUserId(userId, pageable);
        Page<ReviewResponseDto> result = reviews.map(review -> ReviewResponseDto.builder()
                .id(review.getId())
                .userId(review.getUserId())
                .locationId(review.getLocationId())
                .title(review.getTitle())
                .text(review.getText())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<Page<ReviewResponseDto>> getReviewsByLocationId(@PathVariable Long locationId, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Review> reviews = reviewService.getReviewsByLocationId(locationId, pageable);
        Page<ReviewResponseDto> result = reviews.map(review -> ReviewResponseDto.builder()
                .id(review.getId())
                .userId(review.getUserId())
                .locationId(review.getLocationId())
                .title(review.getTitle())
                .text(review.getText())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> updateReview(@PathVariable Long reviewId, UpdateReviewRequestDto requestDto) {
        reviewService.updateReview(reviewId, requestDto.getRating(), requestDto.getTitle(), requestDto.getText());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
