package com.example.demo.admin.dto;

import com.example.demo.admin.domain.entity.Admin;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminDto {

    // 사용자ID
    private String adminId;
    // 패스워드
    private String adminPW;


    public Admin toEntity(){
        Admin admin = Admin.builder()
                .adminId(adminId)
                .adminPW(adminPW)
                .build();
        return admin;
    }

    @Builder
    public AdminDto(String adminId, String adminPW) {
        this.adminId = adminId;
        this.adminPW = adminPW;
    }
}
