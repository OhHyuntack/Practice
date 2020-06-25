package com.example.demo.admin.domain.repository;

import com.example.demo.admin.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminMemberRepository extends JpaRepository<Admin, Long> {

    Admin findByAdminId(String adminId);
}
