package com.poppin.reviewservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class ReviewResponseDto {
    private Long id;
    private Long userId;
    private Long locationId;
    private String title;
    private String text;
    private int rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
