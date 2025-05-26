package com.ssafy.ticket_moa_app.controller;

import com.ssafy.ticket_moa_app.dto.NotiFest;
import com.ssafy.ticket_moa_app.dto.Festival;
import com.ssafy.ticket_moa_app.service.NotiFestService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/noti")
public class NotiFestController {

    @Autowired
    private NotiFestService service;

    @PostMapping("/toggle")
    @Operation(summary = "공연 알림 설정/해제 토글, true면 설정됨 false면 해제됨")
    public boolean toggleNoti(@RequestBody NotiFest notiFest) {
        return service.toggleNoti(notiFest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "사용자가 알림 설정한 공연 리스트 조회")
    public List<Festival> getUserNotiList(@PathVariable("id") String id) {
        return service.getUserNotiList(id);
    }

    @GetMapping("/noti/count/{fesId}")
    @Operation(summary = "해당 공연을 알람 설정한 유저 수를 반환한다.")
    public int getNotiUserCount(@PathVariable("fesId") int fesId) {
        return service.getNotiUserCount(fesId);
    }

    @PostMapping("/noti/add")
    @Operation(summary = "해당 유저가 특정 공연에 알람을 추가한다.")
    public void addNoti(@RequestParam String userId, @RequestParam int fesId) {
        service.addFestivalNoti(userId, fesId);
    }

}
