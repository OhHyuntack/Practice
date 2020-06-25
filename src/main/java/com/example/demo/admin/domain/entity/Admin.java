package com.example.demo.admin.domain.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "admin", uniqueConstraints = {@UniqueConstraint(columnNames = {"admin_id"})})
public class Admin {

    // 어디민ID
    @Id
    @Column(name="admin_id", length = 50)
    private String adminId;

    // 패스워드
    @Column(name="adminPW", length = 50)
    private String adminPW;

    @Builder
    public Admin(String adminId, String adminPW) {
        this.adminId = adminId;
        this.adminPW = adminPW;

    }
}
