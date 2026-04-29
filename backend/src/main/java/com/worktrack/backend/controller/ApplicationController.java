package com.worktrack.backend.controller;

import com.worktrack.backend.dto.ApplicationRequest;
import com.worktrack.backend.dto.StatusUpdateRequest;
import com.worktrack.backend.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public Map<String, Object> apply(@Valid @RequestBody ApplicationRequest request) {
        return applicationService.apply(request);
    }

    @GetMapping("/student/{studentId}")
    public List<Map<String, Object>> getByStudent(@PathVariable Long studentId) {
        return applicationService.getStudentApplications(studentId);
    }

    @GetMapping
    public List<Map<String, Object>> getAll() {
        return applicationService.getAllApplications();
    }

    @PutMapping("/{applicationId}/status")
    public Map<String, Object> updateStatus(@PathVariable Long applicationId,
                                            @Valid @RequestBody StatusUpdateRequest request) {
        return applicationService.updateStatus(applicationId, request);
    }
}
