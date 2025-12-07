package com.vinn.packagebooking.common.data.repository.jdbc;

import com.vinn.packagebooking.common.data.model.Instructor;

import java.util.List;

public interface InstructorJdbcRepository {
    List<Instructor> findAll();
}
