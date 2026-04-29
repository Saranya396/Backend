package com.worktrack.backend.repository;

import com.worktrack.backend.entity.FinalizedWorkRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinalizedWorkRecordRepository extends JpaRepository<FinalizedWorkRecord, Long> {
    List<FinalizedWorkRecord> findByStudentId(Long studentId);
}
