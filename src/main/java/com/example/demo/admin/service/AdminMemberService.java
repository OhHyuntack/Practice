package com.example.demo.admin.service;


import com.example.demo.admin.domain.entity.Admin;
import com.example.demo.admin.domain.repository.AdminMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdminMemberService {

    private AdminMemberRepository adminMemberRepository;


    public Admin findByAdminId(String adminId) {
        return adminMemberRepository.findByAdminId(adminId);
    }


}
