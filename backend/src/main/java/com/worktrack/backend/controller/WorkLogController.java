package com.worktrack.backend.controller;

import com.worktrack.backend.dto.WorkLogRequest;
import com.worktrack.backend.service.WorkLogService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/work-logs")
public class WorkLogController {

    private final WorkLogService workLogService;

    public WorkLogController(WorkLogService workLogService) {
        this.workLogService = workLogService;
    }

    @PostMapping
    public Map<String, Object> save(@Valid @RequestBody WorkLogRequest request) {
        return workLogService.save(request);
    }

    @GetMapping("/student/{studentId}")
    public List<Map<String, Object>> getByStudent(@PathVariable Long studentId) {
        return workLogService.getByStudent(studentId);
    }

    @GetMapping("/admin")
    public List<Map<String, Object>> getAdminRows() {
        return workLogService.getAdminRows();
    }
}
