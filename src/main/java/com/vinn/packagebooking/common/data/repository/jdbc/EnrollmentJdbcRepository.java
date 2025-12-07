package com.vinn.packagebooking.common.data.repository.jdbc;

import com.vinn.packagebooking.common.data.model.Enrollment;

import java.util.List;

public interface EnrollmentJdbcRepository {
    List<Enrollment> findAll();
}
