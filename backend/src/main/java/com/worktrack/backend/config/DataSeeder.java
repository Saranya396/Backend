package com.worktrack.backend.config;

import com.worktrack.backend.entity.Gender;
import com.worktrack.backend.entity.Job;
import com.worktrack.backend.entity.Role;
import com.worktrack.backend.entity.User;
import com.worktrack.backend.repository.JobRepository;
import com.worktrack.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, JobRepository jobRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedUsers();
        seedJobs();
    }

    private void seedUsers() {
        if (userRepository.findByEmail("admin@worktrack.com").isEmpty()) {
            userRepository.save(new User(
                    "Admin User",
                    "admin@worktrack.com",
                    passwordEncoder.encode("admin123"),
                    Gender.OTHER,
                    30,
                    Role.ADMIN
            ));
        }

        if (userRepository.findByEmail("student@worktrack.com").isEmpty()) {
            userRepository.save(new User(
                    "Student User",
                    "student@worktrack.com",
                    passwordEncoder.encode("student123"),
                    Gender.OTHER,
                    20,
                    Role.STUDENT
            ));
        }
    }

    private void seedJobs() {
        if (jobRepository.count() > 0) {
            return;
        }

        jobRepository.save(new Job(
                "Library Assistant",
                "Library Services",
                15.0,
                "10 AM - 2 PM",
                "Assist with shelving, circulation desk, and student queries.",
                LocalDate.now()
        ));

        jobRepository.save(new Job(
                "IT Support Technician",
                "Campus IT",
                15.0,
                "10 AM - 2 PM",
                "Support faculty and labs with device setup, troubleshooting, and ticket updates.",
                LocalDate.now()
        ));
    }
}
