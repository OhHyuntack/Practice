package com.example.demo.admin.controller;

import com.example.demo.admin.domain.entity.Admin;
import com.example.demo.admin.dto.AdminDto;
import com.example.demo.admin.service.AdminMemberService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class AdminMemberController {


    private AdminMemberService adminMemberService;

    @GetMapping("/backend-admin/login")
    public String index() {
        return "admin/login";
    }


    @PostMapping("/admin/login")
    @ResponseBody
    public JSONObject login(AdminDto adminDto, HttpSession session) {
        JSONObject json = new JSONObject();
        String result = "";
        String adminPW = adminDto.getAdminPW();
        Admin adminInfo = adminMemberService.findByAdminId(adminDto.getAdminId());

        if (adminInfo != null) {
            if (adminInfo.getAdminPW().equals(adminPW)) {
                // 세션정보 매핑
                //세션 타임아웃 시간 설정 60*60 1시간 * 24 시간
                session.setMaxInactiveInterval(60 * 60 * 24);
                session.setAttribute("sessionAdminId", adminInfo.getAdminId());

                result = "success";
                json.put("result", result);
                return json;

            } else {
                result = "idFail";
            }
        } else {
            result = "pwFail";
        }
        json.put("result", result);

        return json;
    }

    @PostMapping("/admin/index")
    public String adminIndex(){
        return "admin/index";
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sessionAdminId");

        return "redirect:/backend-admin/login";
    }

    @PostMapping("/admin/insertGoods")
    public String adminInserGoods(){

        return "admin/goods/insertGoods";
    }



}
