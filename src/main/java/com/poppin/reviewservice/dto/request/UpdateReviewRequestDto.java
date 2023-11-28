package com.poppin.reviewservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateReviewRequestDto {

            private Long id;
            private String title;
            private String text;
            private int rating;
}
