package com.worktrack.backend.dto;

import com.worktrack.backend.entity.Gender;
import com.worktrack.backend.entity.Role;

public class AuthResponse {
    private Long id;
    private String fullName;
    private String email;
    private Gender gender;
    private Integer age;
    private Role role;

    public AuthResponse() {
    }

    public AuthResponse(Long id, String fullName, String email, Gender gender, Integer age, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
