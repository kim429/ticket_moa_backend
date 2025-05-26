package com.ssafy.ticket_moa_app.controller;

import com.ssafy.ticket_moa_app.dto.Festival;
import com.ssafy.ticket_moa_app.service.FestivalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/festival")
public class FestivalController {

    @Autowired
    private FestivalService service;

    @GetMapping("/top")
    @Operation(summary = "즐겨찾기가 가장 많은 TOP1 공연을 조회한다.")
    public Festival getTopFestival() {
        return service.getTopFestival();
    }

    @GetMapping("/today")
    @Operation(summary = "티켓팅 날짜가 오늘인 공연 목록을 조회한다.")
    public List<Festival> getTodayFestivals() {
        return service.getTodayFestivals();
    }

    @GetMapping()
    @Operation(summary = "전체 공연 목록을 조회한다.")
    public List<Festival> getAllFestivals() {
        return service.getAllFestivals();
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID로 공연 정보를 단건 조회한다.")
    public Festival getFestivalById(@PathVariable("id") int id) {
        return service.getFestivalById(id);
    }

    @GetMapping("/today/count")
    @Operation(summary = "티켓팅 날짜가 오늘인 공연의 수를 조회한다.")
    public int countTodayFestivals() {
        return service.countTodayFestivals();
    }

    @GetMapping("/today/noti/{userId}")
    @Operation(summary = "오늘 티켓팅 가능한 공연 중, 해당 유저가 알람 설정한 공연 목록 조회")
    public List<Festival> getTodayNotiFestivalsByUser(@PathVariable("userId") String userId) {
        return service.getTodayNotiFestivalsByUser(userId);
    }
}
