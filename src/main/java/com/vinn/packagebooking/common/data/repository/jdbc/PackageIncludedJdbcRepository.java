package com.vinn.packagebooking.common.data.repository.jdbc;

import com.vinn.packagebooking.common.data.model.PackageIncluded;

import java.util.List;

public interface PackageIncludedJdbcRepository {
    List<PackageIncluded> findAll();
}
