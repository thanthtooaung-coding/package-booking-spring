package com.vinn.packagebooking.common.data.repository.jdbc;

import com.vinn.packagebooking.common.data.model.Package;

import java.util.List;

public interface PackageJdbcRepository {
    List<Package> findAll();
}
