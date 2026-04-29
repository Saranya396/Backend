package com.worktrack.backend.dto;

import com.worktrack.backend.entity.Gender;
import com.worktrack.backend.entity.Role;
import jakarta.validation.constraints.*;

public class AuthRequest {

    @NotBlank(message = "Full name is required.")
    @Size(min = 3, message = "Full name must be at least 3 characters.")
    private String fullName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Enter a valid email address.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$",
            message = "Password must contain uppercase, lowercase, number, and special character."
    )
    private String password;

    @NotNull(message = "Gender is required.")
    private Gender gender;

    @NotNull(message = "Age is required.")
    @Min(value = 16, message = "Age must be between 16 and 60.")
    @Max(value = 60, message = "Age must be between 16 and 60.")
    private Integer age;

    @NotNull(message = "Role is required.")
    private Role role;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
