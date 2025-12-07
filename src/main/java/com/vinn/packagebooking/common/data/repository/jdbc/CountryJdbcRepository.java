package com.vinn.packagebooking.common.data.repository.jdbc;

import com.vinn.packagebooking.common.data.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryJdbcRepository {
    List<Country> findAll();
    Optional<Country> findById(Long id);
}
