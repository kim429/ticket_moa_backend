package com.ssafy.ticket_moa_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private int reviewId;
    private String userId;
    private int fesId;
    private String comment;
    private int rating;
    private LocalDateTime createdAt;
}
