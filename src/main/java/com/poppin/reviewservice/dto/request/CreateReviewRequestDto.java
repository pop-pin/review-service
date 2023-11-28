package com.poppin.reviewservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateReviewRequestDto {

        private String title;
        private String text;
        private int rating;
        private Long userId;
        private Long locationId;
}
