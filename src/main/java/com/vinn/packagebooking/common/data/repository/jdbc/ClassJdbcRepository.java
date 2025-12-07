package com.vinn.packagebooking.common.data.repository.jdbc;

import com.vinn.packagebooking.common.data.model.Class;

import java.util.List;

public interface ClassJdbcRepository {
    List<Class> findAll();
}
