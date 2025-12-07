package com.vinn.packagebooking.common.data.repository.jdbc;

import com.vinn.packagebooking.common.data.model.County;

import java.util.List;

public interface CountyJdbcRepository {
    List<County> findAll();
}
