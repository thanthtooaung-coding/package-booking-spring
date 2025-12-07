package com.vinn.packagebooking.common.data.repository.jpa;

import com.vinn.packagebooking.common.data.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUserId(Long userId);
    List<Enrollment> findByEntityIdAndEntityType(Long entityId, Integer entityType);
    List<Enrollment> findByUserIdAndEntityType(Long userId, Integer entityType);
    boolean existsByUserIdAndEntityIdAndEntityType(Long userId, Long entityId, Integer entityType);
}
