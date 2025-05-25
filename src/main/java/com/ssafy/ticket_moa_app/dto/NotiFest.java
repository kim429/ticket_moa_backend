package com.ssafy.ticket_moa_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotiFest {
    private String id;   // 사용자 ID
    private int fesId;   // 공연 ID
}
