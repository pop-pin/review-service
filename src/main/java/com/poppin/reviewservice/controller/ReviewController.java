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

    @PostMapping("")
    public void createReview(@RequestBody CreateReviewRequestDto requestDto) {
        reviewService.createReview(requestDto.getUserId(), requestDto.getLocationId(), requestDto.getRating(), requestDto.getTitle(), requestDto.getText());
    }

    @GetMapping("")
    public ResponseEntity<ReviewResponseDto> getReview(@RequestParam("review_id") Long reviewId) {
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

    @GetMapping("/user")
    public ResponseEntity<Page<ReviewResponseDto>> getReviewsByUserId(@RequestParam("user_id") Long userId, @RequestParam("page") int page, @RequestParam("size") int size) {
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

    @GetMapping("/location")
    public ResponseEntity<Page<ReviewResponseDto>> getReviewsByLocationId(@RequestParam("location_id") Long locationId, @RequestParam("page") int page, @RequestParam("size") int size) {
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

    @PutMapping("")
    public ResponseEntity<Void> updateReview(@RequestParam("review_id") Long reviewId, @RequestBody UpdateReviewRequestDto requestDto) {
        reviewService.updateReview(reviewId, requestDto.getRating(), requestDto.getTitle(), requestDto.getText());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteReview(@RequestParam("review_id") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
