package com.ssafy.ticket_moa_app.controller;

import com.ssafy.ticket_moa_app.dto.User;
import com.ssafy.ticket_moa_app.service.UserService;
import com.ssafy.ticket_moa_app.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    @Operation(summary="로그인한다. 성공적으로 로그인 되면, User객체를 리턴한다.(그리고, loginId라는 쿠키도 response에 전달한다.)")
    public User login(@RequestBody Map<String, String> user, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        User dto = service.login(user.get("id"), user.get("pass"));
        System.out.println(dto);
        if(dto != null) {
            Cookie c = new Cookie("loginId", URLEncoder.encode(dto.getId(), "UTF-8"));
            c.setMaxAge(60*60*24);
            c.setPath("/");
            response.addCookie(c);
        } else {
            model.addAttribute("msg", "아이디 비번 틀림");
        }
        return dto;
    }

    @GetMapping("/logout")
    @Operation(summary = "로그아웃한다. 로그인 상태를 해제하고, 로그인 쿠키를 삭제한다.")
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("loginId", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @PostMapping()
    @Operation(summary="사용자 정보를 추가한다. 성공하면 true를 리턴한다.")
    public Boolean join(@RequestBody User user) {
        service.join(user);
        return true;
    }

    @GetMapping("/isUsed")
    @Operation(summary="request parameter로 전달된 id가 이미 사용중인지 반환한다.")
    public Boolean isUsed(String id) {
        if(service.isUsedId(id)) {
            return false;
        }
        return true;
    }

    @PostMapping("/info")
    @Operation(summary="사용자의 정보와 함께 사용자의 주문내역, 사용자 등급 정보를 반환한다.")
    public Map<String, Object> info(@RequestBody User user) {
        User udto = service.selectUser(user);
        Map<String, Object> map = new HashMap<>();
        map.put("user", udto);
        return map;
    }
}
