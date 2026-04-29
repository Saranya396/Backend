package com.worktrack.backend.repository;

import com.worktrack.backend.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
    List<WorkLog> findByStudentId(Long studentId);
    List<WorkLog> findByJobId(Long jobId);
    Optional<WorkLog> findByStudentIdAndJobId(Long studentId, Long jobId);

    @Query("select coalesce(sum(w.hoursWorked), 0) from WorkLog w where w.student.id = :studentId and w.job.id = :jobId")
    Double sumHoursByStudentIdAndJobId(@Param("studentId") Long studentId, @Param("jobId") Long jobId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from WorkLog w where w.job.id = :jobId")
    void deleteByJobId(@Param("jobId") Long jobId);
}
